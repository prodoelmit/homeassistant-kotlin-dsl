def _ha_scp_impl(ctx):
    script = ctx.actions.declare_file(ctx.label.name + ".sh")

    individual_files = ctx.attr.individual_files
    sync_directories = ctx.attr.sync_directories
    scp_options = ctx.attr.scp_options
    final_dir = ctx.attr.final_dir
    ha_config_dir = ctx.attr.ha_config_dir

    # Parse ha_config_dir into host and path (format: host:/path)
    # We'll do this in bash for simplicity
    content = """
#!/bin/bash
set -xeuo pipefail

cd $BUILD_WORKSPACE_DIRECTORY
pwd

FINAL_CONFIG_DIR="{final_dir}"
HA_CONFIG_DIR="{ha_config_dir}"

# Parse host and remote path
HA_HOST="${{HA_CONFIG_DIR%%:*}}"
HA_PATH="${{HA_CONFIG_DIR#*:}}"

# Function to sync individual files
sync_file() {{
    local file="$1"
    echo "Syncing file: $file"
    # Ensure remote directory exists
    ssh "$HA_HOST" "mkdir -p $HA_PATH/$(dirname "$file")"
    scp {scp_options} "$FINAL_CONFIG_DIR/$file" "$HA_HOST:$HA_PATH/$file"
}}

# Sync individual files
for file in {individual_files}; do
    if [ -f "$FINAL_CONFIG_DIR/$file" ]; then
        sync_file "$file"
    fi
done

# Sync directories
for dir in {sync_directories}; do
    if [ -d "$FINAL_CONFIG_DIR/$dir" ]; then
        echo "Syncing directory: $dir"
        ssh "$HA_HOST" "mkdir -p $HA_PATH/$dir"
        scp {scp_options} -r "$FINAL_CONFIG_DIR/$dir/"* "$HA_HOST:$HA_PATH/$dir/"
    fi
done

echo "Sync completed successfully"
""".format(
        individual_files = " ".join(individual_files),
        sync_directories = " ".join(sync_directories),
        scp_options = scp_options,
        final_dir = final_dir,
        ha_config_dir = ha_config_dir,
    )

    ctx.actions.write(
        output = script,
        content = content,
        is_executable = True,
    )

    return [DefaultInfo(
        executable = script,
        runfiles = ctx.runfiles(files = [script]),
    )]

ha_scp = rule(
    implementation = _ha_scp_impl,
    attrs = {
        "individual_files": attr.string_list(
            mandatory = True,
            doc = "List of individual files to sync",
        ),
        "sync_directories": attr.string_list(
            mandatory = True,
            doc = "List of directories to sync",
        ),
        "scp_options": attr.string(
            default = "",
            doc = "Additional scp options",
        ),
        "final_dir": attr.string(
            mandatory = True,
            doc = "Path to the final configuration directory",
        ),
        "ha_config_dir": attr.string(
            mandatory = True,
            doc = "Path to the Home Assistant configuration directory (host:/path format)",
        ),
    },
    executable = True,
)

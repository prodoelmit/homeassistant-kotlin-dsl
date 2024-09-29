def _ha_sync_impl(ctx):
    script = ctx.actions.declare_file(ctx.label.name + ".sh")

    individual_files = ctx.attr.individual_files
    sync_directories = ctx.attr.sync_directories
    rsync_options = ctx.attr.rsync_options
    final_dir = ctx.attr.final_dir
    ha_config_dir = ctx.attr.ha_config_dir

    content = """
#!/bin/bash
set -xeuo pipefail

cd $BUILD_WORKSPACE_DIRECTORY
pwd

FINAL_CONFIG_DIR="{final_dir}"
HA_CONFIG_DIR="{ha_config_dir}"

# Function to sync individual files
sync_file() {{
    local file="$1"
    echo "Syncing file: $file"
    rsync {rsync_options} "$FINAL_CONFIG_DIR/$file" "$HA_CONFIG_DIR/$file"
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
        rsync {rsync_options} --delete "$FINAL_CONFIG_DIR/$dir/" "$HA_CONFIG_DIR/$dir/"
    fi
done

echo "Sync completed successfully"
""".format(
        individual_files = " ".join(individual_files),
        sync_directories = " ".join(sync_directories),
        rsync_options = rsync_options,
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

ha_sync = rule(
    implementation = _ha_sync_impl,
    attrs = {
        "individual_files": attr.string_list(
            mandatory = True,
            doc = "List of individual files to sync",
        ),
        "sync_directories": attr.string_list(
            mandatory = True,
            doc = "List of directories to sync with --delete option",
        ),
        "rsync_options": attr.string(
            default = "-av --mkpath",
            doc = "Additional rsync options",
        ),
        "final_dir": attr.string(
            mandatory = True,
            doc = "Path to the final configuration directory",
        ),
        "ha_config_dir": attr.string(
            mandatory = True,
            doc = "Path to the Home Assistant configuration directory",
        ),
    },
    executable = True,
)

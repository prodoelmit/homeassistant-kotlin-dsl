load("//rules:install_file_info.bzl", "InstallFileInfo")

def _install_files_impl(ctx):
    install_targets = ctx.attr.targets

    script_content = """
#!/bin/bash
set -xeuo pipefail

# Parse arguments
while [[ $# -gt 0 ]]; do
  case $1 in
    --install-dir)
      INSTALL_DIR="$2"
      shift 2
      ;;
    *)
      echo "Unknown argument: $1"
      exit 1
      ;;
  esac
done

if [ -z "$INSTALL_DIR" ]; then
  echo "Usage: $0 --install-dir <directory>"
  exit 1
fi

"""

    for target in install_targets:
        script_content += 'echo "Installing {0}"\n'.format(target.label)
        script_content += '{0} --install-dir "$INSTALL_DIR"\n\n'.format(target[InstallFileInfo].script.short_path)

    script_content += 'echo "All files have been installed to $INSTALL_DIR"\n'

    out = ctx.actions.declare_file(ctx.label.name + ".sh")
    ctx.actions.write(
        output = out,
        content = script_content,
        is_executable = True,
    )

    runfiles = ctx.runfiles(files = [out])
    for target in install_targets:
        runfiles = runfiles.merge(target[DefaultInfo].default_runfiles)

    return [DefaultInfo(
        executable = out,
        runfiles = runfiles,
    )]

install_files = rule(
    implementation = _install_files_impl,
    attrs = {
        "targets": attr.label_list(
            mandatory = True,
            allow_empty = False,
            providers = [InstallFileInfo],
        ),
    },
    executable = True,
)

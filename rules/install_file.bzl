load("//rules:install_file_info.bzl", "InstallFileInfo")

def _install_file_impl(ctx):
    src = ctx.file.src
    out = ctx.actions.declare_file(ctx.label.name + ".sh")

    ctx.actions.write(
        output = out,
        content = """
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

mkdir -p "$INSTALL_DIR/$(dirname {name})"
cp "{src}" "$INSTALL_DIR/{name}"
chmod 600 "$INSTALL_DIR/{name}"
echo "Installed {src} to $INSTALL_DIR/{name}"
""".format(
            src = src.short_path,
            name = ctx.attr.name,
        ),
        is_executable = True,
    )

    return [
        DefaultInfo(
            executable = out,
            runfiles = ctx.runfiles(files = [src]),
        ),
        InstallFileInfo(script = out),
    ]

install_file = rule(
    implementation = _install_file_impl,
    attrs = {
        "src": attr.label(mandatory = True, allow_single_file = True),
    },
    executable = True,
)

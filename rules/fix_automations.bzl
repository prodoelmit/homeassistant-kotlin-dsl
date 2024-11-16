def _fix_automations_impl(ctx):
    output = ctx.actions.declare_file(ctx.attr.name + "_fixed.yaml")

    # Create the runfiles object that includes all transitive dependencies
    runfiles = ctx.runfiles(files = [ctx.file.automations])
    runfiles = runfiles.merge(ctx.attr._fixer[DefaultInfo].default_runfiles)

    # Get the fixer executable path
    fixer = ctx.executable._fixer

    ctx.actions.run(
        inputs = [ctx.file.automations],
        outputs = [output],
        executable = fixer,
        arguments = [
            ctx.file.automations.path,
            output.path,
        ],
        progress_message = "Fixing entity fields in automation file %s" % ctx.file.automations.short_path,
        # Use the merged runfiles
        tools = [fixer] + ctx.attr._fixer[DefaultInfo].default_runfiles.files.to_list(),
    )

    return [
        DefaultInfo(
            files = depset([output]),
            runfiles = runfiles,
        ),
    ]

fix_automations = rule(
    implementation = _fix_automations_impl,
    attrs = {
        "automations": attr.label(
            mandatory = True,
            allow_single_file = [".yaml", ".yml"],
            doc = "Input automations YAML file to process",
        ),
        "_fixer": attr.label(
            default = "//py:fix_entity_fields_in_automations",
            executable = True,
            cfg = "exec",
        ),
    },
)

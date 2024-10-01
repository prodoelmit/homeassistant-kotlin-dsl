def _process_output(ctx, attr_name, arg_name, output_files, arguments):
    output_file = getattr(ctx.outputs, attr_name)
    if output_file:
        output_files.append(output_file)
        arguments.extend([arg_name, output_file.path])
    return output_files, arguments

def _ha_project_impl(ctx):
    output_files = []
    arguments = []

    output_files, arguments = _process_output(ctx, "automations_out", "--automations", output_files, arguments)
    output_files, arguments = _process_output(ctx, "input_booleans_out", "--input-booleans", output_files, arguments)
    output_files, arguments = _process_output(ctx, "counters_out", "--counters", output_files, arguments)
    output_files, arguments = _process_output(ctx, "scripts_out", "--scripts", output_files, arguments)
    output_files, arguments = _process_output(ctx, "dashboard_out", "--dashboard", output_files, arguments)

    ctx.actions.run(
        outputs = output_files + ctx.outputs.dashboards_outs,
        executable = ctx.executable.generator,
        arguments = arguments,
    )

    return [DefaultInfo(files = depset(output_files))]

ha_project = rule(
    implementation = _ha_project_impl,
    attrs = {
        "automations_out": attr.output(
            mandatory = True,
            doc = "Output file for automations",
        ),
        "input_booleans_out": attr.output(
            mandatory = True,
            doc = "Output file for input booleans helpers",
        ),
        "counters_out": attr.output(
            mandatory = True,
            doc = "Output file for counters helpers",
        ),
        "scripts_out": attr.output(
            mandatory = True,
            doc = "Output file for scripts helpers",
        ),
        "dashboard_out": attr.output(
            doc = "One of expected dashboard outputs. Used to calculate directory for all of them",
        ),
        "dashboards_outs": attr.output_list(
            doc = "Expected outputs for dashboards. They should be in same directory",
        ),
        "generator": attr.label(
            default = Label("//tools:ha_project_generator"),
            executable = True,
            cfg = "exec",
        ),
    },
)

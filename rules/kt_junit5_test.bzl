load("@rules_kotlin//kotlin:jvm.bzl", "kt_jvm_test")

def kt_junit5_test(
        name,
        srcs,
        deps,
        test_package = "",
        test_class = "Test",
        runtime_deps = [],
        data = [],
        env = {},
        resources = [],
        tags = []):
    deps_with_junit = deps + [
        "@maven//:org_junit_jupiter_junit_jupiter_api",
        "@maven//:org_junit_jupiter_junit_jupiter_engine",
        "@maven//:org_junit_jupiter_junit_jupiter_params",
        "@maven//:org_junit_platform_junit_platform_launcher",
        "@maven//:org_junit_platform_junit_platform_reporting",
    ]
    runtime_deps_with_junit = runtime_deps + [
        "@contrib_rules_jvm//java/src/com/github/bazel_contrib/contrib_rules_jvm/junit5",
        #        "@maven//:org_junit_platform_junit_platform_console",
    ]
    contrib_main_class = "com.github.bazel_contrib.contrib_rules_jvm.junit5.JUnit5Runner"
    test_class_full = test_package + "." + test_class

    kt_jvm_test(
        data = data,
        env = env,
        resources = resources,
        name = name,
        srcs = srcs,
        main_class = contrib_main_class,
        args = [
            "--select-package=" + test_package + " --disable-ansi-colors",
        ],
        jvm_flags = [
            "-Dbazel.test_suite=" + test_class_full,
        ],
        deps = deps_with_junit,
        runtime_deps = runtime_deps_with_junit,
        tags = tags,
    )

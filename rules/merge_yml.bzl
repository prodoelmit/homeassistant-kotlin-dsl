load("@aspect_bazel_lib//lib:yq.bzl", "yq")

def dict_merge_yaml(name, srcs):
    yq(
        name = name,
        srcs = srcs,
        expression = ". as $item ireduce ({}; . * $item )",
    )

def list_merge_yaml(name, srcs):
    yq(
        name = name,
        srcs = srcs,
        expression = ". as $item ireduce ([] ; . + $item )",
    )

# Example usage:
# dict_merge_yaml(
#     name = "merged_config",
#     srcs = [
#         "config1.yaml",
#         "config2.yaml",
#         "config3.yaml",
#     ],
# )
#
# list_merge_yaml(
#     name = "merged_list",
#     srcs = [
#         "list1.yaml",
#         "list2.yaml",
#         "list3.yaml",
#     ],
# )

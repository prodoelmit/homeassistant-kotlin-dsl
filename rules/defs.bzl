load("//rules:merge_yml.bzl", _dict_merge_yaml = "dict_merge_yaml", _list_merge_yaml = "list_merge_yaml")
load("//rules:ha_project.bzl", _ha_project = "ha_project")
load("//rules:install_file.bzl", _install_file = "install_file")
load("//rules:install_files.bzl", _install_files = "install_files")

dict_merge_yaml = _dict_merge_yaml
list_merge_yaml = _list_merge_yaml
ha_project = _ha_project
install_file = _install_file
install_files = _install_files

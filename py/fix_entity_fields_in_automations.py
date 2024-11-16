#!/usr/bin/env python3

import sys
import yaml
import argparse

def fix_entity_fields(data):
    """Recursively process YAML data to rename 'entity' to 'entity_id' in condition blocks."""
    if isinstance(data, list):
        return [fix_entity_fields(item) for item in data]
    elif isinstance(data, dict):
        result = {}
        for key, value in data.items():
            if key == 'condition':
                # Process conditions
                if isinstance(value, list):
                    new_conditions = []
                    for condition in value:
                        if isinstance(condition, dict) and 'entity' in condition:
                            # Create new dict with renamed field
                            new_condition = condition.copy()
                            new_condition['entity_id'] = new_condition.pop('entity')
                            new_conditions.append(new_condition)
                        else:
                            new_conditions.append(fix_entity_fields(condition))
                    result[key] = new_conditions
                else:
                    result[key] = fix_entity_fields(value)
            else:
                result[key] = fix_entity_fields(value)
        return result
    return data

def main():
    parser = argparse.ArgumentParser(description='Transform YAML files to rename entity to entity_id in condition blocks')
    parser.add_argument('input_file', help='Input YAML file')
    parser.add_argument('output_file', help='Output YAML file')

    args = parser.parse_args()

    try:
        # Load YAML with preserving order
        with open(args.input_file, 'r') as file:
            data = yaml.safe_load(file)

        # Process the data
        transformed_data = fix_entity_fields(data)

        # Write the transformed YAML
        with open(args.output_file, 'w') as file:
            yaml.dump(transformed_data, file, sort_keys=False, allow_unicode=True)

    except Exception as e:
        print(f"Error processing YAML file: {e}", file=sys.stderr)
        sys.exit(1)

if __name__ == '__main__':
    main()

# Home Assistant Kotlin DSL

A type-safe Kotlin DSL for defining Home Assistant configurations programmatically. Write your automations, scripts, dashboards, and entities in Kotlin and generate YAML configurations automatically.

## Features

- **Type-safe**: Leverage Kotlin's type system to catch errors at compile time
- **Modular**: Organize your configurations into reusable components
- **DRY**: Create abstractions and share common patterns across your automations
- **IDE Support**: Get autocomplete, refactoring, and navigation in your IDE
- **Version Control Friendly**: Better diff and merge experience with programmatic configs

## Quick Start

See the [example template repository](link-to-template) for a complete starter project.

## Project Structure

```
homeassistant-kotlin-dsl/
├── BUILD.bazel           # Bazel build configuration
├── MODULE.bazel          # Bazel module dependencies
├── kt/
│   └── dsl_core/
│       └── kotlin/
│           ├── base/              # Core DSL classes (Automation, HAProject, etc.)
│           ├── actions/           # Home Assistant actions
│           ├── conditions/        # Automation conditions
│           ├── triggers/          # Automation triggers
│           ├── script/blocks/     # Script building blocks
│           ├── entities/          # Entity definitions
│           └── ui/                # Dashboard and UI components
│               ├── cards/         # Card types (entities, conditional, etc.)
│               ├── Dashboard.kt
│               └── View.kt
└── py/                   # Python utilities
```

## Core Concepts

### HAProject

The `HAProject` is the root container for all your Home Assistant configurations:

```kotlin
fun main(args: Array<String>) = HAProjectCommand {
    // Add automations
    automation { ... }

    // Add scripts
    script { ... }

    // Add dashboards
    dashboard { ... }

    // Add entities
    inputBoolean { ... }
    counter { ... }
    binarySensor { ... }
}.main(args)
```

### Automations

Automations consist of three parts: triggers, conditions (optional), and actions.

```kotlin
automation {
    id = "turn_on_lights_at_sunset"
    alias = "Turn on lights at sunset"
    description = "Automatically turn on living room lights when sun sets"

    triggers {
        stateTrigger {
            entity("sun.sun")
            transitionTo = "below_horizon"
        }
    }

    conditions {
        state {
            entity("input_boolean.auto_lights")
            state = "on"
        }
    }

    actions {
        service {
            service = ActionNames.Light.TurnOn
            target("light.living_room")
            data["brightness"] = 255
        }
    }
}
```

### Triggers

Common trigger types:

#### State Trigger
```kotlin
stateTrigger {
    entities("binary_sensor.motion_sensor")
    transitionTo = "on"
    forDuration {
        seconds = 30
    }
}
```

#### Time Pattern Trigger
```kotlin
timePatternTrigger {
    hours = "/1"  // Every hour
}
```

#### Numeric State Trigger
```kotlin
numericStateTrigger {
    entity("sensor.temperature")
    above = 25
}
```

#### NFC Tag Trigger
```kotlin
nfcTag {
    tag("your_tag_id")
}
```

### Conditions

Conditions determine whether automation actions should run:

```kotlin
conditions {
    // Simple state condition
    state {
        entity("input_boolean.guest_mode")
        state = "off"
    }

    // Numeric state condition
    numericState {
        entity("sensor.temperature")
        below = 20
    }

    // Logical operators
    and {
        state { ... }
        state { ... }
    }

    or {
        state { ... }
        state { ... }
    }

    not {
        state { ... }
    }
}
```

### Actions

Actions define what happens when an automation triggers:

```kotlin
actions {
    // Call a service
    service {
        service = ActionNames.Light.TurnOn
        target("light.bedroom")
        data["brightness"] = 128
        data["color_temp"] = 300
    }

    // Delay
    delay {
        seconds = 5
    }

    // Conditional execution
    ifElse {
        conditions {
            state {
                entity("binary_sensor.door")
                state = "open"
            }
        }
        then {
            service { ... }
        }
        otherwise {
            service { ... }
        }
    }

    // Call another script
    callAction {
        action = "script.my_custom_script"
    }
}
```

### Scripts

Scripts are reusable sequences of actions:

```kotlin
val myScript = HAScript {
    id("turn_on_movie_mode")
    alias = "Movie Mode"

    blocks {
        // Turn off lights
        callAction {
            action = ActionNames.Light.TurnOff
            entity("light.living_room")
        }

        // Wait a moment
        delay {
            seconds = 2
        }

        // Turn on TV
        callAction {
            action = ActionNames.Switch.TurnOn
            entity("switch.tv")
        }
    }
}
```

### Dashboards

Create Lovelace dashboards programmatically:

```kotlin
dashboard {
    path = "main"
    title = "Main Dashboard"
    icon = "mdi:home"
    showInSidebar = true

    view {
        title = "Living Room"
        icon = "mdi:sofa"

        cards {
            entitiesCard {
                title = "Lights"
                entities {
                    entity("light.living_room")
                    entity("light.kitchen")
                }
            }

            conditionalCard {
                conditions {
                    numericState {
                        entity("sensor.temperature")
                        above = 25
                    }
                }
                cards {
                    entityCard {
                        entity = "climate.ac"
                    }
                }
            }
        }
    }
}
```

### Card Types

Available card types:

- `entitiesCard`: Display multiple entities
- `entityCard`: Display a single entity
- `conditionalCard`: Show cards based on conditions
- `mushroomTemplateCard`: Mushroom template cards
- `verticalStackCard`: Stack cards vertically
- `horizontalStackCard`: Stack cards horizontally
- `gridCard`: Arrange cards in a grid

### Entities

Define helper entities:

```kotlin
// Input Boolean
inputBoolean {
    alias = "guest_mode"
    name = "Guest Mode"
    icon = "mdi:account-multiple"
}

// Counter
counter {
    alias = "days_since_cleaning"
    name = "Days Since Last Cleaning"
    icon = "mdi:broom"
    initial = 0
    step = 1
}

// Binary Sensor
binarySensor {
    name = "Custom Sensor"
    deviceClass = "motion"
    // ... configuration
}
```

## Building the Project

This project uses Bazel as its build system:

```bash
# Build the project
bazel build //...

# Run tests
bazel test //...

# Run the configuration generator
bazel run //your_project:main -- --output-dir=/path/to/homeassistant/config
```

## Advanced Patterns

### Creating Reusable Abstractions

You can create higher-level abstractions for common patterns:

```kotlin
// Define a reusable pattern
fun HAProject.motionLightAutomation(
    motionSensor: String,
    light: String,
    delayMinutes: Int = 5
) {
    automation {
        id = "motion_light_${light.replace(".", "_")}"

        triggers {
            stateTrigger {
                entities(motionSensor)
                transitionTo = "on"
            }
        }

        actions {
            service {
                service = ActionNames.Light.TurnOn
                target(light)
            }
        }
    }

    automation {
        id = "motion_light_off_${light.replace(".", "_")}"

        triggers {
            stateTrigger {
                entities(motionSensor)
                transitionTo = "off"
                forDuration {
                    minutes = delayMinutes
                }
            }
        }

        actions {
            service {
                service = ActionNames.Light.TurnOff
                target(light)
            }
        }
    }
}

// Usage
HAProjectCommand {
    motionLightAutomation("binary_sensor.hallway_motion", "light.hallway")
    motionLightAutomation("binary_sensor.bathroom_motion", "light.bathroom", delayMinutes = 2)
}
```

### Extension Functions

Use Kotlin extension functions for cleaner syntax:

```kotlin
fun HAProject.livingRoomAutomations() {
    automation {
        id = "living_room_morning_lights"
        // ...
    }
    automation {
        id = "living_room_evening_lights"
        // ...
    }
}

// In main
HAProjectCommand {
    livingRoomAutomations()
}
```

### Type-Safe Entity References

Define entity references as typed objects:

```kotlin
object Entities {
    object Lights {
        const val livingRoom = "light.living_room"
        const val bedroom = "light.bedroom"
        const val kitchen = "light.kitchen"
    }

    object Sensors {
        const val temperature = "sensor.living_room_temperature"
        const val humidity = "sensor.living_room_humidity"
    }
}

// Usage
actions {
    service {
        service = ActionNames.Light.TurnOn
        target(Entities.Lights.livingRoom)
    }
}
```

## Why Use a DSL?

### Before (YAML)
```yaml
- id: "turn_on_all_lights"
  alias: "Turn on all lights at sunset"
  trigger:
    - platform: sun
      event: sunset
  action:
    - service: light.turn_on
      target:
        entity_id: light.living_room
    - service: light.turn_on
      target:
        entity_id: light.bedroom
    - service: light.turn_on
      target:
        entity_id: light.kitchen
```

### After (Kotlin DSL)
```kotlin
automation {
    id = "turn_on_all_lights"
    alias = "Turn on all lights at sunset"

    triggers {
        stateTrigger {
            entity("sun.sun")
            transitionTo = "below_horizon"
        }
    }

    actions {
        listOf(
            Entities.Lights.livingRoom,
            Entities.Lights.bedroom,
            Entities.Lights.kitchen
        ).forEach { light ->
            service {
                service = ActionNames.Light.TurnOn
                target(light)
            }
        }
    }
}
```

Benefits:
- IDE autocomplete and type checking
- Reusable functions and abstractions
- Better refactoring capabilities
- Compile-time error detection
- Full power of Kotlin (loops, conditionals, functions)

## License

[Add your license here]

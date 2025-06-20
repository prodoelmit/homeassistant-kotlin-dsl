package kt.dsl_core.kotlin.actions

object ActionNames {
    object Switch {
        val Toggle = "switch.toggle"
        val TurnOn = "switch.turn_on"
        val TurnOff = "switch.turn_off"
    }
    object Light {
        val Toggle = "light.toggle"
        val TurnOn = "light.turn_on"
        val TurnOff = "light.turn_off"
    }

    object MediaPlayer {
        val TurnOn = "media_player.turn_on"
        val TurnOff = "media_player.turn_off"
        val Toggle = "media_player.toggle"
        val VolumeUp = "media_player.volume_up"
        val VolumeDown = "media_player.volume_down"
        val VolumeSet = "media_player.volume_set"
        val VolumeMute = "media_player.volume_mute"
        val MediaPlayPause = "media_player.media_play_pause"
        val MediaPlay = "media_player.media_play"
        val MediaPause = "media_player.media_pause"
        val MediaStop = "media_player.media_stop"
        val MediaNextTrack = "media_player.media_next_track"
        val MediaPreviousTrack = "media_player.media_previous_track"
        val ClearPlaylist = "media_player.clear_playlist"
        val ShuffleSet = "media_player.shuffle_set"
        val RepeatSet = "media_player.repeat_set"
        val PlayMedia = "media_player.play_media"
        val SelectSource = "media_player.select_source"
        val SelectSoundMode = "media_player.select_sound_mode"
        val Join = "media_player.join"
        val Unjoin = "media_player.unjoin"
    }

    object Climate {
        val SetAuxHeat = "climate.set_aux_heat"
        val SetPresetMode = "climate.set_preset_mode"
        val SetTemperature = "climate.set_temperature"
        val SetHumidity = "climate.set_humidity"
        val SetFanMode = "climate.set_fan_mode"
        val SetHvacMode = "climate.set_hvac_mode"
        val SetSwingMode = "climate.set_swing_mode"
        val TurnOn = "climate.turn_on"
        val TurnOff = "climate.turn_off"
        val Toggle = "climate.toggle"
    }

    object InputBoolean {
        val TurnOn = "input_boolean.turn_on"
        val TurnOff = "input_boolean.turn_off"
        val Toggle = "input_boolean.toggle"
        val Reload = "input_boolean.reload"
    }

    object Counter {
        val Increment = "counter.increment"
        val Decrement = "counter.decrement"
        val Reset = "counter.reset"
        val SetValue = "counter.set_value"
    }

    object ToDo {
        val GetItems = "todo.get_items"
        val AddItem = "todo.add_item"
        val UpdateItem = "todo.update_item"
        val RemoveItem = "todo.remove_item"
    }

    object Remote {
        val SendCommand = "remote.send_command"
    }

    object Timer {
        val Cancel = "timer.cancel"
        val Change = "timer.change"
        val Finish = "timer.finish"
        val Pause = "timer.pause"
        val Reload = "timer.reload"
        val Start = "timer.start"
    }

    object Cover {
        val OpenCover = "cover.open_cover"
        val CloseCover = "cover.close_cover"
        val StopCover = "cover.stop_cover"
        val Toggle = "cover.toggle"
        val OpenCoverTilt = "cover.open_cover_tilt"
        val CloseCoverTilt = "cover.close_cover_tilt"
        val StopCoverTilt = "cover.stop_cover_tilt"
        val ToggleTilt = "cover.toggle_tilt"
        val SetPosition = "cover.set_cover_position"
    }

}

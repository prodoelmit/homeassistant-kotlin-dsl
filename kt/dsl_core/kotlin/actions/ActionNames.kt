package kt.dsl_core.kotlin.actions

object ActionNames {
    object Switch {
        val Toggle = "switch.toggle"
        val TurnOn = "switch.turn_on"
        val TurnOff = "switch.turn_off"
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
}

package com.fnatic.session.ro

import com.fasterxml.jackson.annotation.JsonProperty
import com.fnatic.measurement.ro.MeasurementRO
import com.fnatic.session.model.Session

data class SessionRO(
        var id: Long? = null,
        @JsonProperty("player_name")
        var playerName: String? = null,
        @JsonProperty("game_session_name")
        var gameSessionName: String? = null,
        var speed: Double? = 0.0,
        var accuracy: Double? = 0.0,
        var measurements: List<MeasurementRO>? = emptyList()
) {
    fun toSession(speed: Double, accuracy: Double): Session {
        return Session(
                id, playerName, gameSessionName, speed, accuracy, measurements?.map { it.toMeasurement() }
        )
    }
}

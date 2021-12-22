package com.fnatic.session.model

import com.fnatic.measurement.model.Measurement
import com.fnatic.session.ro.SessionIdNameRO
import com.fnatic.session.ro.SessionRO
import java.io.Serializable
import javax.persistence.*

@Table
@Entity(name = "session")
data class Session(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        var playerName: String? = null,
        var gameSessionName: String? = null,
        var speed: Double? = 0.0,
        var accuracy: Double? = 0.0,
        @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = [CascadeType.ALL])
        @JoinColumn(name = "session_id")
        var measurements: List<Measurement>? = emptyList()
) : Serializable {
    fun toSessionRO(): SessionRO {
        return SessionRO(id, playerName, gameSessionName, speed, accuracy, measurements?.map { it.toMeasurementRO() })
    }

    fun toSessionIdNameRO(): SessionIdNameRO {
        return SessionIdNameRO(id, playerName)
    }
}

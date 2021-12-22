package com.fnatic.measurement.model

import com.fnatic.measurement.ro.MeasurementRO
import com.fnatic.session.model.Session
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "measurement")
data class Measurement(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        var time: Long? = null,
        var type: MeasurementType,
        var value: String? = null,
        var grade: Double = 0.0,
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "session_id" )
        var session: Session? = null
) : Serializable {
    fun toMeasurementRO(): MeasurementRO {
        return MeasurementRO(
                id = id,
                time = time,
                type = MeasurementType.fromMeasurementType(type),
                value = value,
                grade = grade
        )
    }
}

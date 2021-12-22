package com.fnatic.measurement.ro

import com.fnatic.measurement.model.Measurement
import com.fnatic.measurement.model.MeasurementType

data class MeasurementRO(
        var id: Long? = null,
        var time: Long? = null,
        var type: String,
        var value: Any? = null,
        var grade: Double = 0.0
) {
    fun toMeasurement(): Measurement {
        return Measurement(
                id = id,
                time = time,
                type = MeasurementType.toMeasurementType(type),
                value = value.toString(),
                grade = grade
        )
    }
}

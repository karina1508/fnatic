package com.fnatic.measurement

import com.fnatic.measurement.model.Measurement
import com.fnatic.measurement.model.MeasurementGrades
import com.fnatic.measurement.model.MeasurementType
import com.fnatic.measurement.ro.MeasurementRO
import org.springframework.stereotype.Service

@Service
class MeasurementServiceImpl : MeasurementService {
    override fun countSpeed(measurementGrades: MeasurementGrades): Double {
        return measurementGrades.move * 0.3 + measurementGrades.bomb * 0.7
    }

    override fun countAccuracy(measurementGrades: MeasurementGrades): Double {
        return measurementGrades.misses * 0.5 + measurementGrades.headshot * 0.6 + measurementGrades.body * 0.4
    }

    override fun getMeasurementGrades(measurements: List<MeasurementRO>): MeasurementGrades {
        val measurementGrades = MeasurementGrades()
        measurements.forEach { measurement ->
            when (MeasurementType.toMeasurementType(measurement.type)) {
                MeasurementType.MOVE -> {
                    measurement.grade = countIntValue(measurement.time, measurementGrades.move, 105, 105..250)
                    measurementGrades.move += measurement.grade
                    measurementGrades.moveSize++
                }
                MeasurementType.BOMB -> {
                    measurement.grade = countIntValue(measurement.time, measurementGrades.bomb, 4000)
                    measurementGrades.bomb += measurement.grade
                    measurementGrades.bombSize++
                }
                MeasurementType.BODY -> {
                    measurement.grade = countBooleanValue(measurement.value, measurementGrades.body, 80)
                    measurementGrades.body += measurement.grade
                    measurementGrades.bodySize++
                }
                MeasurementType.HEADSHOT -> {
                    measurement.grade = countBooleanValue(measurement.value, measurementGrades.headshot, 100)
                    measurementGrades.headshot += measurement.grade
                    measurementGrades.headshotSize++
                }
                MeasurementType.MISSES -> {
                    measurement.grade = countIntValue(measurement.value, measurementGrades.misses, 60, 60..400)
                    measurementGrades.misses += measurement.grade
                    measurementGrades.missesSize++
                }
            }
        }
        measurementGrades.moveSize.takeIf { it != 0 }?.let { measurementGrades.move /= it }
        measurementGrades.bombSize.takeIf { it != 0 }?.let { measurementGrades.bomb /= it }
        measurementGrades.missesSize.takeIf { it != 0 }?.let { measurementGrades.misses /= it }
        measurementGrades.headshotSize.takeIf { it != 0 }?.let { measurementGrades.headshot /= it }
        measurementGrades.bodySize.takeIf { it != 0 }?.let { measurementGrades.body /= it }
        return measurementGrades
    }

    fun countIntValue(value: Any?, count: Double, minValueRange: Int, secondValueRange: IntRange? = null): Double {
        val intValue = value?.toString()?.toIntOrNull()
        var valueCount = count
        if (intValue != null) {
            if (intValue < minValueRange) {
                valueCount += 100
            }
            if (secondValueRange != null && intValue in secondValueRange) {
                valueCount += 70
            }
        }
        return valueCount
    }

    fun countBooleanValue(value: Any?, body: Double, points: Int): Double {
        val booleanValue = value?.toString()?.toBoolean()
        var bombCount = body
        if (booleanValue != null && booleanValue) {
            bombCount += 80;
        }
        return bombCount
    }
}

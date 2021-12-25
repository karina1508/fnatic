package com.fnatic.measurement

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
                    measurement.grade = countGradeForLong(measurement.time, 105, 105..250)
                    measurementGrades.move += measurement.grade
                    measurementGrades.moveSize++
                }
                MeasurementType.BOMB -> {
                    measurement.grade = countGradeForLong(measurement.time,  4000)
                    measurementGrades.bombSize++
                }
                MeasurementType.BODY -> {
                    measurement.grade = countBooleanValue(measurement.value, 80)
                    measurementGrades.body += measurement.grade
                    measurementGrades.bodySize++
                }
                MeasurementType.HEADSHOT -> {
                    measurement.grade = countBooleanValue(measurement.value, 100)
                    measurementGrades.headshot += measurement.grade
                    measurementGrades.headshotSize++
                }
                MeasurementType.MISSES -> {
                    measurement.grade = countGradeForLong(measurement.value, 60, 60..400)
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

    fun countGradeForLong(value: Any?, minValueRange: Int, secondValueRange: IntRange? = null): Double {
        var valueCount = 0.0
        val intValue = value?.toString()?.toLongOrNull()
        if (intValue != null) {
            if (intValue < minValueRange) {
                valueCount = 100.0
            }
            if (secondValueRange != null && intValue in secondValueRange) {
                valueCount = 70.0
            }
        }
        return valueCount
    }

    fun countBooleanValue(value: Any?, points: Int): Double {
        val booleanValue = value?.toString()?.toBoolean()
        var bombCount = 0.0
        if (booleanValue != null && booleanValue) {
            bombCount += 80
        }
        return bombCount
    }
}

package com.fnatic.measurement

import com.fnatic.measurement.model.MeasurementGrades
import com.fnatic.measurement.ro.MeasurementRO

interface MeasurementService {
    fun countSpeed(measurementGrades:MeasurementGrades):Double
    fun countAccuracy(measurementGrades:MeasurementGrades):Double
    fun getMeasurementGrades(measurements:List<MeasurementRO>): MeasurementGrades
}

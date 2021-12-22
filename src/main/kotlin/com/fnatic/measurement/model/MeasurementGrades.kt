package com.fnatic.measurement.model

data class MeasurementGrades(
        var move: Double = 0.0,
        var bomb: Double = 0.0,
        var misses: Double = 0.0,
        var headshot: Double = 0.0,
        var body: Double = 0.0,
        var moveSize: Int = 0,
        var bombSize: Int = 0,
        var bodySize: Int = 0,
        var missesSize: Int = 0,
        var headshotSize: Int = 0
)

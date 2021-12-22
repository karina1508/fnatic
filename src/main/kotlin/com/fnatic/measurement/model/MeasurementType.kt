package com.fnatic.measurement.model

enum class MeasurementType(val param: String) {
    MOVE(param = "Move"), BODY(param ="Body"), MISSES(param ="Misses"), HEADSHOT(param ="Headshot"), BOMB(param ="Bomb");

    companion object {

        fun toMeasurementType(value: String): MeasurementType {
            return values().firstOrNull { it.param == value } ?: throw Exception("missing measurement type or type $value isn't correct")
        }

        fun fromMeasurementType(type: MeasurementType): String {
            return type.name
        }
    }
}

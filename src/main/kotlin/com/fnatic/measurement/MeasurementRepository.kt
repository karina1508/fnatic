package com.fnatic.measurement

import com.fnatic.measurement.model.Measurement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MeasurementRepository:JpaRepository<Measurement, Long>

package com.fnatic.session

import com.fnatic.measurement.MeasurementService
import com.fnatic.session.ro.SessionIdNameRO
import com.fnatic.session.ro.SessionRO
import org.springframework.stereotype.Component
import java.lang.Exception

@Component("sessionHandler")
class SessionHandlerImpl(
        private val sessionService: SessionService,
        private val measurementService: MeasurementService
) : SessionHandler {

    override fun save(session: SessionRO): Long {
        val measurementGrades = measurementService.getMeasurementGrades(session.measurements ?: listOf())
        val speed = measurementService.countSpeed(measurementGrades)
        val accuracy = measurementService.countAccuracy(measurementGrades)
        return sessionService.save(session.toSession(speed, accuracy))
    }

    override fun update(session: SessionRO): SessionRO {
        session.id?.let { sessionService.getById(it) } ?: throw Exception("session by id ${session.id} not found")
        val measurementGrades = measurementService.getMeasurementGrades(session.measurements ?: listOf())
        val speed = measurementService.countSpeed(measurementGrades)
        val accuracy = measurementService.countAccuracy(measurementGrades)
        return sessionService.update(session.toSession(speed, accuracy)).toSessionRO()
    }

    override fun getAll(): List<SessionIdNameRO> {
        return sessionService.getAll().map { it.toSessionIdNameRO() }
    }

    override fun getById(id: Long): SessionRO? {
        return sessionService.getById(id)?.toSessionRO()
    }
}

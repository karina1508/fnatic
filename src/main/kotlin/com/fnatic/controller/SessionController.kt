package com.fnatic.controller

import com.fnatic.measurement.MeasurementRepository
import com.fnatic.session.SessionHandler
import com.fnatic.session.SessionRepository
import com.fnatic.session.ro.SessionIdNameRO
import com.fnatic.session.ro.SessionRO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/session")
class SessionController(
        private val sessionHandler: SessionHandler
        ) {
    @PostMapping
    fun save(@RequestBody session: SessionRO): Long = sessionHandler.save(session)

    @PutMapping
    fun update(@RequestBody session: SessionRO): SessionRO = sessionHandler.update(session)

    @GetMapping
    fun getAll(): List<SessionIdNameRO> = sessionHandler.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): SessionRO? = sessionHandler.getById(id)
}

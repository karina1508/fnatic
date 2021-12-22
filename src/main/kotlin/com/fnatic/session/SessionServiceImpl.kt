package com.fnatic.session

import com.fnatic.session.model.Session
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class SessionServiceImpl(
        private val sessionRepository: SessionRepository
) : SessionService {
    override fun save(session: Session): Long {
        return sessionRepository.save(session).id ?: throw Exception("session wasn't savfed")
    }

    override fun update(session: Session): Session {
        return sessionRepository.save(session)
    }

    override fun getAll(): List<Session> {
        return sessionRepository.findAll()
    }

    override fun getById(id: Long): Session? {
        return sessionRepository.findById(id).orElse(null)
    }
}

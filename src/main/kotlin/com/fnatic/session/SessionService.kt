package com.fnatic.session

import com.fnatic.session.model.Session

interface SessionService {
    fun save(session: Session): Long
    fun update(session: Session): Session
    fun getAll(): List<Session>
    fun getById(id: Long): Session?
}

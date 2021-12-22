package com.fnatic.session

import com.fnatic.session.ro.SessionIdNameRO
import com.fnatic.session.ro.SessionRO

interface SessionHandler {
    fun save(session: SessionRO): Long
    fun update(session: SessionRO): SessionRO
    fun getAll(): List<SessionIdNameRO>
    fun getById(id: Long): SessionRO?
}

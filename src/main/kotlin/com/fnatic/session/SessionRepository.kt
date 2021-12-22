package com.fnatic.session

import com.fnatic.session.model.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionRepository:JpaRepository<Session, Long>

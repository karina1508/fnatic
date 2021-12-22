package com.fnatic.session.ro

import com.fasterxml.jackson.annotation.JsonProperty

data class SessionIdNameRO(
        var id: Long? = null,
        @JsonProperty("game_session_name")
        var gameSessionName: String? = null
)

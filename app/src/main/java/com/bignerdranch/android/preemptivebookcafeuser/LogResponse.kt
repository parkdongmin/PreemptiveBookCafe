package com.bignerdranch.android.preemptivebookcafeuser

import java.time.LocalDateTime

data class LogResponse (
    val id : Long,
    val logEvent : String,
    val seatId : Long,
    val classNo : Long,
    val logTime : LocalDateTime
)
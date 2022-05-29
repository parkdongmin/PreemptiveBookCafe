package com.bignerdranch.android.preemptivebookcafeuser

import java.time.LocalDateTime
import java.util.*


data class Seats constructor(
    val id : Long,
    val user : UserResponse,
    val status : String,
    val leftOn : LocalDateTime,
    val registerAt : LocalDateTime,
    val updatedAt : LocalDateTime
    )


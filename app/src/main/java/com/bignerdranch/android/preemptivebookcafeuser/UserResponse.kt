package com.bignerdranch.android.preemptivebookcafeuser

import java.time.LocalDateTime

data class UserResponse (
   val id : Long,
   val user : Long,
   val email : String,
   val createdAt : LocalDateTime,
   val updatedAt : LocalDateTime,
   val isDeleted : Boolean
)
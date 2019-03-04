package com.ovrbach.qapitalchallengerebooted.domain.entity

import java.io.Serializable

data class User(
    val userId: UserId,
    val displayName: String,
    val avatarUrl: String
) : Serializable
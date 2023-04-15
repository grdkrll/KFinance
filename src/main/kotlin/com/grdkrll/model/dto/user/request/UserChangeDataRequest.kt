package com.grdkrll.model.dto.user.request

class UserChangeDataRequest(
    val handle: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
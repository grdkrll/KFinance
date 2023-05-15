package com.grdkrll.model.dto.group.request

class ChangeGroupRequest(
    val name: String,
    val handle: String,
    val password: String,
    val confirmPassword: String
)
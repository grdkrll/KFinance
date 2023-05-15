package com.grdkrll.model.dto.user.response

import com.grdkrll.model.dao.User

class UserSignResponse(val token: String, user: User) {
    val id = user.id.value
    val name = user.name
    val email: String = user.email
    val handle: String = user.handle
}
package com.grdkrll.model.dto.user.response

import com.grdkrll.model.dao.User

class UserSignResponse(token: String, user: User) {
    val token = token
    val id = user.id.value
    val email: String = user.email
    val handle: String = user.handle
}
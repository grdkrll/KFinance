package com.grdkrll.model.dto.user.response

import com.grdkrll.model.dao.User

class UserResponse(val user: User) {
    val id = user.id.value
    val email = user.email
}
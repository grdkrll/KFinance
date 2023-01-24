package com.grdkrll.model.dto.user.response

import com.grdkrll.model.dao.User

class UserResponse(user: User) {
    val id: Int = user.id.value
    val handle: String = user.handle
}
package com.grdkrll.model.dto.user.response

import com.grdkrll.model.dao.User

/**
 * A Response Class with basic information about a User and a JWT token of that User
 *
 * @param token the JWT Token
 * @param user the User
 */
class UserSignResponse(val token: String, user: User) {
    val id = user.id.value
    val name = user.name
    val email: String = user.email
    val handle: String = user.handle
}
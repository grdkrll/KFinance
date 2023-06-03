package com.grdkrll.model.dto.user.request

/**
 * A Request Class used to Sign In the User
 *
 * @property login the login of the User (either handle or email)
 * @property password the password of the User
 */
class UserSignInRequest (
    val login: String,
    val password: String
)
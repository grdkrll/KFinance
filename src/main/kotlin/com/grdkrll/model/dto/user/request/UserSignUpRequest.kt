package com.grdkrll.model.dto.user.request

/**
 * A Request Class used to Sign Up a User
 *
 * @property name the name for the User Account
 * @property handle the handle for the User Account
 * @property email the email for the User Account
 * @property password the password for the User Account
 */
class UserSignUpRequest (
    val name: String,
    val handle: String,
    val email: String,
    val password: String,
)
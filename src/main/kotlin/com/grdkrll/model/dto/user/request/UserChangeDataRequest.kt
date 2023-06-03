package com.grdkrll.model.dto.user.request

/**
 * A Request Class used for changing information about the User Account
 *
 * @property name a new name for the User
 * @property handle a new handle for the User
 * @property email a new email for the User
 * @property password a new password for the User (leave empty to keep the current password)
 * @property confirmPassword the current password of the User
 */
class UserChangeDataRequest(
    val name: String,
    val handle: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
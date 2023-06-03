package com.grdkrll.model.dto.group.request

/**
 * A Request Class used for changing information about the Group
 *
 * @property name a new name for the Group
 * @property handle a new handle for the Group
 * @property password a new Password for the Group (leave empty to keep current password)
 * @property confirmPassword the current Password of the Group
 */
class ChangeGroupRequest(
    val name: String,
    val handle: String,
    val password: String,
    val confirmPassword: String
)
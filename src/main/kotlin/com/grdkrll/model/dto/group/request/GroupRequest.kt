package com.grdkrll.model.dto.group.request

/**
 * A Request Class used for Creating a new Group
 *
 * @property name the name of the new Group
 * @property handle the handle of the new Group
 * @property password the password of the new Group
 */
class GroupRequest (
    val name: String,
    val handle: String,
    val password: String
)
package com.grdkrll.model.dto.group.request

/**
 * A Request Class used for Joining a Group
 *
 * @property handle the Handle of the Group
 * @property password the Password of the Group
 */
class MemberRequest (
    val handle: String,
    val password: String
)
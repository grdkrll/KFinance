package com.grdkrll.model.dto.group.request

/**
 * A Request Class used for Removing a Member from the Group
 *
 * @property groupHandle the Handle of the Group
 * @property userHandle the Handle of the Member
 */
class RemoveMemberRequest(
    val groupHandle: String,
    val userHandle: String
)
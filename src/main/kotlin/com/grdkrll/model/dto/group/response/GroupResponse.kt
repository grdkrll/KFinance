package com.grdkrll.model.dto.group.response

import com.grdkrll.model.dao.Group

/**
 * A Response Class with basic information about a Group
 *
 * @param group the Group with which to response
 */
class GroupResponse(group: Group) {
    val id: Int = group.id.value
    val name: String = group.name
    val handle: String = group.handle
    val ownerId: Int = group.ownerId.id.value
}
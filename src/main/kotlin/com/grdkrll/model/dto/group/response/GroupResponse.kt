package com.grdkrll.model.dto.group.response

import com.grdkrll.model.dao.Group

class GroupResponse(group: Group) {
    val id: Int = group.id.value
    val handle: String = group.handle
}
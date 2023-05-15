package com.grdkrll.model.dto.group.response

import com.grdkrll.model.dao.User

class MemberResponse(user: User) {
    val name: String = user.name
    val handle: String = user.handle
}
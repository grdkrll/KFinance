package com.grdkrll.model.dto.group.response

import com.grdkrll.model.dao.User

/**
 * A Response Class with basic information about a Member of a Group
 *
 * @param user the Member of the Group
 */
class MemberResponse(user: User) {
    val name: String = user.name
    val handle: String = user.handle
}
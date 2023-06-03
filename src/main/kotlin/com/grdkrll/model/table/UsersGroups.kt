package com.grdkrll.model.table

import org.jetbrains.exposed.dao.id.IntIdTable

/**
 * An Entity Class used for managing relationships of Users and Groups
 */
object UsersGroups : IntIdTable() {
    val user = reference("user", Users)
    val group = reference("group", Groups)
}
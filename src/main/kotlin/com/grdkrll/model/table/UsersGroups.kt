package com.grdkrll.model.table

import org.jetbrains.exposed.dao.id.IntIdTable


object UsersGroups : IntIdTable() {
    val user = reference("user", Users)
    val group = reference("group", Groups)
}
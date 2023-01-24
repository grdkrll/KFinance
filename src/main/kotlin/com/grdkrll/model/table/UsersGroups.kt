package com.grdkrll.model.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table


object UsersGroups : IntIdTable() {
    val user = reference("user", Users)
    val group = reference("group", Groups)
}
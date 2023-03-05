package com.grdkrll.model.table

import org.jetbrains.exposed.dao.id.IntIdTable

object Groups : IntIdTable() {
    val name = varchar("name", 255)
    val ownerId = reference("owner", Users)
    val handle = varchar("handle", 255)
}
package com.grdkrll.model.table

import org.jetbrains.exposed.dao.id.IntIdTable

/**
 * An Entity Class used for storing Groups in the Database
 */
object Groups : IntIdTable() {
    val name = varchar("name", 255)
    val ownerId = reference("owner", Users)
    val handle = varchar("handle", 255)
    val passwordHash = varchar("password_hash", 63)
}
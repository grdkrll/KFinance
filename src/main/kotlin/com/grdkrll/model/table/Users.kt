package com.grdkrll.model.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.CurrentTimestamp
import org.jetbrains.exposed.sql.javatime.timestamp

/**
 * An Entity Class used for storing Users in the Database
 */
object Users: IntIdTable() {
    val name = varchar("name", 255)
    val email = varchar("email", 255)
    val handle = varchar("handle", 255)
    val passwordHash = varchar("password_hash", 63)
    val timestamp = timestamp("timestamp").defaultExpression(CurrentTimestamp())
}
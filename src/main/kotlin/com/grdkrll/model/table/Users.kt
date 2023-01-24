package com.grdkrll.model.table

import com.grdkrll.model.dao.MoneyTransaction
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.CurrentTimestamp
import org.jetbrains.exposed.sql.javatime.timestamp

object Users: IntIdTable() {
    val email = varchar("email", 255)
    val handle = varchar("handle", 255)
    val passwordHash = varchar("password_hash", 63)
    val timestamp = timestamp("timestamp").defaultExpression(CurrentTimestamp())

}
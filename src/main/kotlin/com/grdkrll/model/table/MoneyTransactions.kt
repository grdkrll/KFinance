package com.grdkrll.model.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.CurrentTimestamp
import org.jetbrains.exposed.sql.javatime.timestamp

/**
 * An Entity Class used for storing Transactions in the Database
 */
object MoneyTransactions : IntIdTable() {
    val message = varchar("message", 255)
    val category = varchar("category", 255)
    val sum = decimal("sum", 64, 3)
    val type = bool("type")
    val ownerId = reference("owner", Users)
    val groupId = integer("groupId")
    val timestamp = timestamp("timestamp").defaultExpression(CurrentTimestamp())
}
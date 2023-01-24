package com.grdkrll.model.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.CurrentTimestamp
import org.jetbrains.exposed.sql.javatime.timestamp

object MoneyTransactions : IntIdTable() {
    val type = varchar("type", 255)
    val sum = decimal("sum", 64, 3)
    val owner = reference("owner", Users)
    val timestamp = timestamp("timestamp").defaultExpression(CurrentTimestamp())
}
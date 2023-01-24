package com.grdkrll.model.table

import org.jetbrains.exposed.sql.Table

object UsersTransactions: Table() {
    val user = reference("user", Users)
    val transaction = reference("transaction", MoneyTransactions)
}
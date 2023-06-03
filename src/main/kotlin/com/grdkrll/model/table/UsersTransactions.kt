package com.grdkrll.model.table

import org.jetbrains.exposed.sql.Table

/**
 * An Entity Class used for managing relationships of Users and Transactions
 */
object UsersTransactions: Table() {
    val user = reference("user", Users)
    val transaction = reference("transaction", MoneyTransactions)
}
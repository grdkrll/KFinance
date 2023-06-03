package com.grdkrll.model.dto.money_transaction.response

import com.grdkrll.model.dao.MoneyTransaction

/**
 * A Response Class with basic information about a Transaction
 *
 * @param transaction the Transaction
 * @param ownerHandle the handle of the Owner User of the Transaction
 */
class MoneyTransactionResponse(transaction: MoneyTransaction, val ownerHandle: String) {
    val id: Int = transaction.id.value
    val message: String = transaction.message
    val type: Boolean = transaction.type
    val category: String = transaction.category
    val sum: Double = transaction.sum.toDouble()
    val timestamp: String = transaction.timestamp.toString()
}
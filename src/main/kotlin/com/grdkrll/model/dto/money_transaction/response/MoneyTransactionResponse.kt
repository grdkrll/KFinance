package com.grdkrll.model.dto.money_transaction.response

import com.grdkrll.model.dao.MoneyTransaction
import java.time.temporal.ChronoField

class MoneyTransactionResponse(transaction: MoneyTransaction) {
    val id: Int = transaction.id.value
    val type: Boolean = transaction.type
    val category: String = transaction.category
    val sum: Double = transaction.sum.toDouble()
    val timestamp: Long = transaction.timestamp.getLong(ChronoField.INSTANT_SECONDS)
}
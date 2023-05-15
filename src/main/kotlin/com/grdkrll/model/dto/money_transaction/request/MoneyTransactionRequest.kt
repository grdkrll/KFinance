package com.grdkrll.model.dto.money_transaction.request

import com.grdkrll.service.TransactionCategory

class MoneyTransactionRequest (
    val message: String,
    val type: Boolean,
    val groupId: Int,
    val category: TransactionCategory,
    val sum: Double,
)
package com.grdkrll.model.dto.money_transaction.response

class TransactionPageResponse(
    val result: List<MoneyTransactionResponse>,
    val totalCount: Int
)
package com.grdkrll.model.dto.money_transaction.response

import com.grdkrll.model.dao.MoneyTransaction

class MoneyTransactionResponse(transaction: MoneyTransaction) {
    val id: Int = transaction.id.value
}
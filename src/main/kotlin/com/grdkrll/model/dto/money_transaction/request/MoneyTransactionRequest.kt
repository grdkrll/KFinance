package com.grdkrll.model.dto.money_transaction.request

import com.grdkrll.service.TransactionType
import java.math.BigDecimal

class MoneyTransactionRequest (
    val type: TransactionType,
    val sum: BigDecimal,
) {
}
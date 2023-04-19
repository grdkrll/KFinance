package com.grdkrll.model.dto.money_transaction.request

import com.grdkrll.service.TransactionCategory
import java.math.BigDecimal

class MoneyTransactionRequest (
    val type: Boolean,
    val groupId: Int,
    val category: TransactionCategory,
    val sum: BigDecimal,
)
package com.grdkrll.model.dto.money_transaction.response

/**
 * A Response Class with a Page of Transactions
 *
 * @param result a List of Transactions for the Page
 * @param totalCount the total number of Transactions
 */
class TransactionPageResponse(
    val result: List<MoneyTransactionResponse>,
    val totalCount: Int
)
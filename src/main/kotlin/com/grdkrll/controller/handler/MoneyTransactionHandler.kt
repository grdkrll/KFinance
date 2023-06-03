package com.grdkrll.controller.handler

import com.grdkrll.model.dto.money_transaction.response.TransactionPageResponse
import io.ktor.server.application.*

/**
 * A Handler Class used to handle requests about Transactions
 */
interface MoneyTransactionHandler {
    /**
     * Used to create a new Transaction
     */
    suspend fun addMoneyTransaction(call: ApplicationCall)

    /**
     * Used to get a Page of Transactions [TransactionPageResponse]
     */
    suspend fun getPage(call: ApplicationCall)

    /**
     * Used to get a Total sum of all Transactions of the User
     */
    suspend fun getTotal(call: ApplicationCall)

}
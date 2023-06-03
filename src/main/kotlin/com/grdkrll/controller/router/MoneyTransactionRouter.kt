package com.grdkrll.controller.router

import com.grdkrll.controller.handler.MoneyTransactionHandler
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

/**
 * A Router Function used to route calls to the API about Transactions to specific functions of [MoneyTransactionHandler]
 */
fun Route.transactionRouting() {
    val handler by inject<MoneyTransactionHandler>()

    /**
     * Used to indicate that calls listed below require authentication via JWT Bearer Token
     */
    authenticate("jwt") {
        get("/get_all_transactions") { handler.getPage(this.call) }
        post("/add_transaction") { handler.addMoneyTransaction(this.call) }
        get("/total") { handler.getTotal(this.call) }
    }
}
package com.grdkrll.controller.handler

import io.ktor.server.application.*

interface MoneyTransactionHandler {
    suspend fun addMoneyTransaction(call: ApplicationCall)

    suspend fun getPage(call: ApplicationCall)

    suspend fun getTotal(call: ApplicationCall)

}
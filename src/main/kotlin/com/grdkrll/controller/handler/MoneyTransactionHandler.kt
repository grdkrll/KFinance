package com.grdkrll.controller.handler

import io.ktor.server.application.*

interface MoneyTransactionHandler {
    suspend fun addMoneyTransaction(call: ApplicationCall)

    suspend fun getAllByUser(call: ApplicationCall)

    suspend fun getAllByGroup(call: ApplicationCall)

    suspend fun findById(call: ApplicationCall)

}
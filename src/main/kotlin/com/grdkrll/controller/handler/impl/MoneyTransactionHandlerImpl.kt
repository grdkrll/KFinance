package com.grdkrll.controller.handler.impl

import com.grdkrll.controller.handler.MoneyTransactionHandler
import com.grdkrll.model.dto.exception.transaction.EmptyTransactionIdException
import com.grdkrll.model.dto.money_transaction.request.MoneyTransactionRequest
import com.grdkrll.service.MoneyTransactionService
import com.grdkrll.service.SortType
import com.grdkrll.service.TimePeriodType
import com.grdkrll.service.TransactionCategory
import com.grdkrll.util.getSession
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MoneyTransactionHandlerImpl : MoneyTransactionHandler, KoinComponent {
    private val service by inject<MoneyTransactionService>()

    override suspend fun addMoneyTransaction(call: ApplicationCall) {
        val moneyTransaction = call.receive<MoneyTransactionRequest>()
        val response = service.addTransaction(call.getSession(), moneyTransaction)
        call.respond(response)
    }

    override suspend fun getAllByUser(call: ApplicationCall) {
        val sortQuery = SortType.valueOf(call.request.queryParameters["sort"] ?: "NEW")
        val typeQuery = TransactionCategory.valueOf(call.request.queryParameters["type"] ?: "ALL")
        val timeQuery = TimePeriodType.valueOf(call.request.queryParameters["time"] ?: "ALL")
        val response = service.getAllByUser(call.getSession(), typeQuery, timeQuery, sortQuery)
        call.respond(response)
    }

    override suspend fun findById(call: ApplicationCall) {
        val transactionId = call.parameters["id"]?.toInt() ?: throw EmptyTransactionIdException()
        val response = service.findById(call.getSession(), transactionId)
        call.respond(response)
    }
}
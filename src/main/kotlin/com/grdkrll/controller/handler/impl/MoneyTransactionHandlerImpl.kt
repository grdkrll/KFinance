package com.grdkrll.controller.handler.impl

import com.grdkrll.controller.handler.MoneyTransactionHandler
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

    override suspend fun getPage(call: ApplicationCall) {
        val groupId = (call.request.queryParameters["group_id"]?.toInt() ?: 0)
        val recent = (call.request.queryParameters["recent"]?.toInt() ?: 0) == 1
        val page = call.request.queryParameters["page"]?.toInt() ?: 0
        val sortQuery = SortType.valueOf(call.request.queryParameters["sort"] ?: "NEW")
        val typeQuery = TransactionCategory.valueOf(call.request.queryParameters["category"] ?: "ALL")
        val timeQuery = TimePeriodType.valueOf(call.request.queryParameters["time"] ?: "ALL")
        val response = if (groupId == 0) service.getPageByUser(
            call.getSession(),
            recent,
            page,
            typeQuery,
            timeQuery,
            sortQuery
        ) else service.getPageByGroup(call.getSession(), groupId, recent, page, typeQuery, timeQuery, sortQuery)
        call.respond(response)
    }

    override suspend fun getTotal(call: ApplicationCall) {
        val groupId = call.request.queryParameters["groupId"]?.toInt() ?: 0
        val timeQuery = TimePeriodType.valueOf(call.request.queryParameters["time"] ?: "ALL")
        val typeQuery = TransactionCategory.valueOf(call.request.queryParameters["category"] ?: "ALL")
        val response = service.getTotal(call.getSession(), groupId, timeQuery, typeQuery)
        call.respond(response)
    }
}
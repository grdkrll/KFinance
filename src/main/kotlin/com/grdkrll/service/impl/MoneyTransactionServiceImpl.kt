package com.grdkrll.service.impl

import com.grdkrll.model.dao.MoneyTransaction
import com.grdkrll.model.dao.User
import com.grdkrll.model.dto.exception.transaction.TransactionNotFoundException
import com.grdkrll.model.dto.exception.user.UserNotFoundException
import com.grdkrll.model.dto.money_transaction.request.MoneyTransactionRequest
import com.grdkrll.model.dto.money_transaction.response.MoneyTransactionResponse
import com.grdkrll.model.table.MoneyTransactions
import com.grdkrll.service.MoneyTransactionService
import com.grdkrll.service.SortType
import com.grdkrll.service.TimePeriodType
import com.grdkrll.service.TransactionType
import com.grdkrll.util.UserSession
import io.ktor.server.util.*
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.CurrentTimestamp
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Date
import java.sql.Timestamp
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Calendar


class MoneyTransactionServiceImpl : MoneyTransactionService {
    private fun List<MoneyTransaction>.sortBy(sortQuery: SortType): List<MoneyTransaction> {
        return when (sortQuery) {
            SortType.OLD -> {
                sortedBy { it.timestamp }.reversed()
            }

            SortType.RISING -> {
                sortedBy { it.sum }
            }

            SortType.FALLING -> {
                sortedBy { it.sum }.reversed()
            }

            else -> {
                sortedBy { it.timestamp }
            }
        }
    }

    private fun List<MoneyTransaction>.filterByTime(timeQuery: TimePeriodType): List<MoneyTransaction> {
        val t = Timestamp(System.currentTimeMillis())
        return when(timeQuery) {
            TimePeriodType.TODAY -> {
                val today = Date(t.time).toInstant()
                val tomorrow = Date(t.time + 24 * 60 * 60 * 1000)
                filter { it.timestamp.isAfter(today) && it.timestamp.isBefore(tomorrow.toInstant()) }
            }

            else -> {
                filter { true }
            }
        }
    }


    override fun addTransaction(session: UserSession, request: MoneyTransactionRequest): MoneyTransactionResponse {
        return transaction {
            val moneyTransaction = MoneyTransaction.new {
                type = request.type.name
                sum = request.sum
                owner = User.findById(session.id)?.id ?: throw UserNotFoundException()
            }
            MoneyTransactionResponse(moneyTransaction)
        }
    }

    override fun getAllByUser(session: UserSession, type: TransactionType, timeQuery: TimePeriodType, sortQuery: SortType): List<MoneyTransactionResponse> {
        return transaction {
            MoneyTransaction.find { MoneyTransactions.owner eq session.id }.map { it }
        }.sortBy(sortQuery).filter { type.name == "ALL" || it.type == type.name }.filterByTime(timeQuery).map { MoneyTransactionResponse(it) }
    }

    override fun findById(session: UserSession, id: Int): MoneyTransactionResponse {
        return transaction {
            val moneyTransaction = MoneyTransaction.findById(id) ?: throw TransactionNotFoundException()
            MoneyTransactionResponse(moneyTransaction)
        }
    }
}
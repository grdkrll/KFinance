package com.grdkrll.service.impl

import com.grdkrll.model.dao.MoneyTransaction
import com.grdkrll.model.dao.User
import com.grdkrll.model.dao.UserGroup
import com.grdkrll.model.dto.exception.transaction.TransactionNotFoundException
import com.grdkrll.model.dto.exception.user.UserNotFoundException
import com.grdkrll.model.dto.money_transaction.request.MoneyTransactionRequest
import com.grdkrll.model.dto.money_transaction.response.MoneyTransactionResponse
import com.grdkrll.model.dto.money_transaction.response.TransactionPageResponse
import com.grdkrll.model.table.MoneyTransactions
import com.grdkrll.model.table.UsersGroups
import com.grdkrll.service.MoneyTransactionService
import com.grdkrll.service.SortType
import com.grdkrll.service.TimePeriodType
import com.grdkrll.service.TransactionCategory
import com.grdkrll.util.UserSession
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Date
import java.sql.Timestamp


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
        return when (timeQuery) {
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
                category = request.category.name
                sum = request.sum
                type = request.type
                groupId = request.groupId
                ownerId = User.findById(session.id) ?: throw UserNotFoundException()
            }
            MoneyTransactionResponse(moneyTransaction)
        }
    }

    override fun getAllByUser(
        session: UserSession,
        type: TransactionCategory,
        timeQuery: TimePeriodType,
        sortQuery: SortType
    ): TransactionPageResponse {
        return transaction {
            val res = MoneyTransaction.find { MoneyTransactions.ownerId eq session.id }.map { it }.sortBy(sortQuery)
                .filter { type.name == "ALL" || it.category == type.name }.filterByTime(timeQuery)
                .map { MoneyTransactionResponse(it) }
            TransactionPageResponse(res, res.size)
        }
    }

    override fun getAllByGroup(
        session: UserSession,
        groupId: Int,
        type: TransactionCategory,
        timeQuery: TimePeriodType,
        sortQuery: SortType
    ): TransactionPageResponse {
        return transaction {
            if (UserGroup.find { UsersGroups.user eq session.id and (UsersGroups.group eq groupId) }
                    .singleOrNull() == null) throw Exception("User is not a member")
            val res =
                MoneyTransaction.find { MoneyTransactions.groupId eq session.id and (MoneyTransactions.type eq true) }
                    .map { MoneyTransactionResponse(it) }
            TransactionPageResponse(res, res.size)
        }
    }

    override fun findById(session: UserSession, id: Int): MoneyTransactionResponse {
        return transaction {
            val moneyTransaction = MoneyTransaction.findById(id) ?: throw TransactionNotFoundException()
            MoneyTransactionResponse(moneyTransaction)
        }
    }
}
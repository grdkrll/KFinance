package com.grdkrll.service.impl

import com.grdkrll.model.dao.MoneyTransaction
import com.grdkrll.model.dao.User
import com.grdkrll.model.dao.UserGroup
import com.grdkrll.model.dto.exception.user.UserNotFoundException
import com.grdkrll.model.dto.money_transaction.request.MoneyTransactionRequest
import com.grdkrll.model.dto.money_transaction.response.MoneyTransactionResponse
import com.grdkrll.model.dto.money_transaction.response.TotalResponse
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
import java.math.BigDecimal
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.WeekFields
import java.util.*
import kotlin.math.min


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
        val zone = ZoneId.systemDefault()
        val current = LocalDate.now()
        return when (timeQuery) {
            TimePeriodType.TODAY -> {
                filter {
                    LocalDate.ofInstant(it.timestamp, zone).dayOfMonth == current.dayOfMonth &&
                            LocalDate.ofInstant(it.timestamp, zone).month == current.month &&
                            LocalDate.ofInstant(it.timestamp, zone).year == current.year
                }
            }

            TimePeriodType.THIS_WEEK -> {
                val weekFields = WeekFields.of(Locale.getDefault())
                filter {
                    LocalDate.ofInstant(it.timestamp, zone).get(weekFields.weekOfWeekBasedYear()) ==
                            LocalDate.ofInstant(it.timestamp, zone).get(weekFields.weekOfWeekBasedYear())
                }
            }

            TimePeriodType.THIS_MONTH -> {
                filter {
                    LocalDate.ofInstant(it.timestamp, zone).month == current.month &&
                            LocalDate.ofInstant(it.timestamp, zone).year == current.year
                }

            }

            else -> {
                filter { true }
            }
        }
    }


    override fun addTransaction(session: UserSession, request: MoneyTransactionRequest): MoneyTransactionResponse {
        return transaction {
            var totalSum = request.sum
            if (
                listOf(
                    TransactionCategory.HOUSING.name,
                    TransactionCategory.TRANSPORTATION.name,
                    TransactionCategory.FOOD.name,
                    TransactionCategory.UTILITIES.name,
                    TransactionCategory.CLOTHING.name,
                    TransactionCategory.MEDICAL.name,
                    TransactionCategory.SUPPLIES.name,
                    TransactionCategory.PERSONAL.name,
                    TransactionCategory.DEBT.name,
                    TransactionCategory.INVESTING.name,
                    TransactionCategory.EDUCATION.name,
                    TransactionCategory.SAVINGS.name,
                    TransactionCategory.DONATIONS.name,
                    TransactionCategory.ENTERTAINMENT.name
                ).find { it == request.category.name } != null
            ) {
                totalSum *= -1
            }
            val user = User.findById(session.id) ?: throw UserNotFoundException()
            val moneyTransaction = MoneyTransaction.new {
                message = request.message
                category = request.category.name
                sum = BigDecimal(totalSum)
                type = request.type
                groupId = request.groupId
                ownerId = user
            }
            MoneyTransactionResponse(moneyTransaction, user.handle)
        }
    }

    override fun getPageByUser(
        session: UserSession,
        recent: Boolean,
        page: Int,
        type: TransactionCategory,
        timeQuery: TimePeriodType,
        sortQuery: SortType
    ): TransactionPageResponse {
        val pagination = if (recent) 5 else 30
        return transaction {
            val list = MoneyTransaction.find { MoneyTransactions.ownerId eq session.id }.map { it }.reversed()
            val res = list.subList(pagination * (page - 1), min(list.size, pagination * page)).sortBy(sortQuery)
                .filter { type.name == "ALL" || it.category == type.name }.filterByTime(timeQuery)
                .map {
                    val owner = User.findById(it.ownerId.id) ?: throw UserNotFoundException()
                    MoneyTransactionResponse(it, owner.handle)
                }
            TransactionPageResponse(res, (list.size + 29) / 30)
        }
    }

    override fun getPageByGroup(
        session: UserSession,
        groupId: Int,
        recent: Boolean,
        page: Int,
        type: TransactionCategory,
        timeQuery: TimePeriodType,
        sortQuery: SortType
    ): TransactionPageResponse {
        val pagination = if (recent) 5 else 30
        return transaction {
            if (UserGroup.find { UsersGroups.user eq session.id and (UsersGroups.group eq groupId) }
                    .singleOrNull() == null) throw Exception("User is not a member")
            val list =
                MoneyTransaction.find { MoneyTransactions.groupId eq session.id and (MoneyTransactions.type eq true) }
                    .map { it }.reversed()
            val res = list.subList(pagination * (page - 1), min(list.size, pagination * page))
                .filter { type.name == "ALL" || it.category == type.name }.filterByTime(timeQuery)
                .map {
                    val owner = User.findById(it.ownerId.id) ?: throw UserNotFoundException()
                    MoneyTransactionResponse(it, owner.handle)
                }
            TransactionPageResponse(res, (list.size + 29) / 30)
        }
    }

    override fun getTotal(
        session: UserSession,
        groupId: Int,
        timeQuery: TimePeriodType,
        category: TransactionCategory
    ): TotalResponse {
        return transaction {
            var list =
                MoneyTransaction.find { MoneyTransactions.ownerId eq session.id }
                    .map { it }
            if (groupId != 0) {
                list =
                    MoneyTransaction.find { MoneyTransactions.ownerId eq session.id and (MoneyTransactions.type eq true and (MoneyTransactions.groupId eq groupId)) }
                        .map { it }
            }
            var total = 0.toDouble()
            for (it in list.filter { category.name == "ALL" || it.category == category.name }.filterByTime(timeQuery)) {
                total += it.sum.toDouble()
                println(it.sum.toDouble())
            }
            TotalResponse(total)
        }
    }
}

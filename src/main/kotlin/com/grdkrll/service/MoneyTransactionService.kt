package com.grdkrll.service

import com.grdkrll.model.dto.money_transaction.request.MoneyTransactionRequest
import com.grdkrll.model.dto.money_transaction.response.MoneyTransactionResponse
import com.grdkrll.model.dto.money_transaction.response.TotalResponse
import com.grdkrll.model.dto.money_transaction.response.TransactionPageResponse
import com.grdkrll.util.UserSession

enum class SortType {
    NEW,
    OLD,
    RISING,
    FALLING
}

enum class TransactionCategory {
    // expenses
    HOUSING,
    TRANSPORTATION,
    FOOD,
    UTILITIES,
    CLOTHING,
    MEDICAL,
    SUPPLIES,
    PERSONAL,
    DEBT,
    INVESTING,
    EDUCATION,
    SAVINGS,
    DONATIONS,
    ENTERTAINMENT,

    // income
    SALARY,
    GIFTS,
    DIVIDENDS,
    ALL
}

enum class TimePeriodType {
    TODAY,
    THIS_WEEK,
    THIS_MONTH,
    ALL
}

interface MoneyTransactionService {
    fun addTransaction(session: UserSession, request: MoneyTransactionRequest): MoneyTransactionResponse

    fun getPageByUser(
        session: UserSession,
        recent: Boolean,
        page: Int,
        type: TransactionCategory,
        timeQuery: TimePeriodType,
        sortQuery: SortType
    ): TransactionPageResponse

    fun getPageByGroup(
        session: UserSession,
        groupId: Int,
        recent: Boolean,
        page: Int,
        type: TransactionCategory,
        timeQuery: TimePeriodType,
        sortQuery: SortType
    ): TransactionPageResponse

    fun getTotal(
        session: UserSession,
        groupId: Int,
        timeQuery: TimePeriodType,
        category: TransactionCategory
    ): TotalResponse
}
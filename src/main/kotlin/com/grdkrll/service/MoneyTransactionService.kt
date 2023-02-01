package com.grdkrll.service

import com.grdkrll.model.dto.money_transaction.request.MoneyTransactionRequest
import com.grdkrll.model.dto.money_transaction.response.MoneyTransactionResponse
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
    THIS_YEAR,
    ALL
}

interface MoneyTransactionService {
    fun addTransaction(session: UserSession, request: MoneyTransactionRequest) : MoneyTransactionResponse

    fun getAllByUser(session: UserSession, type: TransactionCategory, timeQuery: TimePeriodType, sortQuery: SortType) : List<MoneyTransactionResponse>

    fun findById(session: UserSession, id: Int): MoneyTransactionResponse
}
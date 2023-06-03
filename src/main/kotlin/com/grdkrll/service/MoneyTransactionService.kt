package com.grdkrll.service

import com.grdkrll.model.dto.money_transaction.request.MoneyTransactionRequest
import com.grdkrll.model.dto.money_transaction.response.MoneyTransactionResponse
import com.grdkrll.model.dto.money_transaction.response.TotalResponse
import com.grdkrll.model.dto.money_transaction.response.TransactionPageResponse
import com.grdkrll.util.UserSession

/**
 * An Enum Class that lists all currently possible way to sort the Transactions
 */
enum class SortType {
    NEW,
    OLD,
    RISING,
    FALLING
}

/**
 * An Enum Class that lists all currently possible Transactions Categories
 */
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

/**
 * An Enum Class that lists all currently possible Time Periods by which to filter
 */
enum class TimePeriodType {
    TODAY,
    THIS_WEEK,
    THIS_MONTH,
    ALL
}

/**
 * A Service Class used for processing API calls about Transactions
 */
interface MoneyTransactionService {
    /**
     * Used to create a new Transaction and add it to the database
     *
     * @param session the User Session from the JWT Token
     * @param request the Request data to create the Transaction
     */
    fun addTransaction(session: UserSession, request: MoneyTransactionRequest): MoneyTransactionResponse

    /**
     * Used to get a Page of Transactions of the User
     *
     * @param session the User Session from the JWT Token
     * @param recent indicates the number of Transactions to return (if set to 0, up to 30 transactions are returned, if set to 1, up to 5 transactions are returned)
     * @param page the page number for which to return the Transactions
     * @param type indicates the Category by which to filter the Transactions
     * @param timeQuery indicates the Time Period by which to filter the Transactions
     * @param sortQuery indicates the Sort Type by which to sort the Transactions
     */
    fun getPageByUser(
        session: UserSession,
        recent: Boolean,
        page: Int,
        type: TransactionCategory,
        timeQuery: TimePeriodType,
        sortQuery: SortType
    ): TransactionPageResponse

    /**
     * Used to get a Page of Transactions of a Group
     *
     * @param session the User Session from the JWT Token
     * @param groupId the id of the Group for which to return the Transactions
     * @param recent indicates the number of Transactions to return (if set to 0, up to 30 transactions are returned, if set to 1, up to 5 transactions are returned)
     * @param page the page number for which to return the Transactions
     * @param type indicates the Category by which to filter the Transactions
     * @param timeQuery indicates the Time Period by which to filter the Transactions
     * @param sortQuery indicates the Sort Type by which to sort the Transactions
     */
    fun getPageByGroup(
        session: UserSession,
        groupId: Int,
        recent: Boolean,
        page: Int,
        type: TransactionCategory,
        timeQuery: TimePeriodType,
        sortQuery: SortType
    ): TransactionPageResponse

    /**
     * Used to get a Total Sum of the Transactions
     *
     * @param session the User Session from the JWT Token
     * @param groupId the id of the Group for which to return the Transactions (if set to 0, indicates that Transactions will be only of the User, otherwise gets Transactions of the Group with id of [groupId])
     * @param category indicates the Category by which to filter the Transactions
     * @param timeQuery indicates the Time Period by which to filter the Transactions
     */
    fun getTotal(
        session: UserSession,
        groupId: Int,
        timeQuery: TimePeriodType,
        category: TransactionCategory
    ): TotalResponse
}
package com.grdkrll.model.dto.money_transaction.request

import com.grdkrll.service.TransactionCategory

/**
 * A Request Class used for creating a new Transaction
 *
 * @property message the message of the new Transaction
 * @property type the type of the new Transaction (if set to 0, the Transaction belongs only to the User, if set to 1, the Transaction also belongs to a Group)
 * @property groupId the id of the Group to which the new Transaction belongs (if set to 0 the Transaction belongs only to the User)
 * @property category the category to which the new Transaction belongs
 * @property sum the total sum of the new Transaction
 */
class MoneyTransactionRequest (
    val message: String,
    val type: Boolean,
    val groupId: Int,
    val category: TransactionCategory,
    val sum: Double,
)
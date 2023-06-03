package com.grdkrll.model.dao

import com.grdkrll.model.table.MoneyTransactions
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.time.Instant

/**
 * A DAO Class used for Transactions
 *
 * @property message the message of the Transaction
 * @property category the category of the Transaction
 * @property sum the total sum of the Transaction
 * @property type the type of the Transaction (if set to 0 Transaction? belongs only to the User if set to 1, belongs to some group)
 * @property ownerId the Owner of the Transaction (reference to [User])
 * @property groupId the id of the Group to which the Transaction belongs (if type is set to 0, [groupId] == id of [ownerId])
 * @property timestamp the timestamp of when Transaction was created an instance of [Instant]
 */
class MoneyTransaction(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<MoneyTransaction>(MoneyTransactions)

    var message by MoneyTransactions.message
    var category by MoneyTransactions.category
    var sum by MoneyTransactions.sum
    var type by MoneyTransactions.type
    var ownerId by User referencedOn MoneyTransactions.ownerId
    var groupId by MoneyTransactions.groupId
    var timestamp by MoneyTransactions.timestamp

}
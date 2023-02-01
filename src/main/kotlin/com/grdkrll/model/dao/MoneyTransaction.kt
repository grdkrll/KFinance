package com.grdkrll.model.dao

import com.grdkrll.model.table.MoneyTransactions
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MoneyTransaction(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<MoneyTransaction>(MoneyTransactions)

    var category by MoneyTransactions.category
    var sum by MoneyTransactions.sum
    var type by MoneyTransactions.type
    var ownerId by MoneyTransactions.ownerId
    var timestamp by MoneyTransactions.timestamp

}
package com.grdkrll.model.dao

import com.grdkrll.model.table.MoneyTransactions
import com.grdkrll.model.table.UsersTransactions
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MoneyTransaction(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<MoneyTransaction>(MoneyTransactions)

    var type by MoneyTransactions.type
    var sum by MoneyTransactions.sum
    var owner by MoneyTransactions.owner
    var timestamp by MoneyTransactions.timestamp

}
package com.grdkrll.model.dao

import com.grdkrll.model.table.Users
import com.grdkrll.model.table.UsersTransactions
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<User>(Users)

    var email by Users.email
    var handle by Users.handle
    var passwordHash by Users.passwordHash

    var moneyTransactions by MoneyTransaction via UsersTransactions
}
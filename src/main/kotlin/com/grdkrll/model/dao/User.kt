package com.grdkrll.model.dao

import com.grdkrll.model.table.Users
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * A DAO Class used for Users
 *
 * @property name the name of the User
 * @property email the email of the User
 * @property handle the handle of the User
 * @property passwordHash the Hash of the Password of the User
 */
class User(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<User>(Users)

    var name by Users.name
    var email by Users.email
    var handle by Users.handle
    var passwordHash by Users.passwordHash
}
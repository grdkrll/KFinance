package com.grdkrll.model.dao

import com.grdkrll.model.table.Groups
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Group(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Group>(Groups)

    var name by Groups.name
    var handle by Groups.handle
    var ownerId by Groups.ownerId
    var passwordHash by Groups.passwordHash

//    var members by User via UsersGroups
}
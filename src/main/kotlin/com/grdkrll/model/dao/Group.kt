package com.grdkrll.model.dao

import com.grdkrll.model.table.Groups
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Group(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Group>(Groups)

    var name by Groups.name
    var handle by Groups.handle
    var owner by Groups.owner
}
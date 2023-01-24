package com.grdkrll.model.dao

import com.grdkrll.model.table.UsersGroups
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Table

class UserGroup(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserGroup>(UsersGroups)
    var user by UsersGroups.user
    var group by UsersGroups.group

}
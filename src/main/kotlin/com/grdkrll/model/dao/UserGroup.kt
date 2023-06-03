package com.grdkrll.model.dao

import com.grdkrll.model.table.UsersGroups
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * A DAO Class used for managing Members of Groups
 *
 * @property user an id of a User
 * @property group an id of a Group of the User
 */
class UserGroup(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserGroup>(UsersGroups)
    var user by UsersGroups.user
    var group by UsersGroups.group

}
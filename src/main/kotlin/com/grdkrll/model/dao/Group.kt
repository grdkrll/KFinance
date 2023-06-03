package com.grdkrll.model.dao

import com.grdkrll.model.table.Groups
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * A DAO Class used for Groups
 *
 * @property name the name of the Group
 * @property handle the handle of the Group (used for identification by the User)
 * @property ownerId the Owner of the Group (reference to [User])
 * @property passwordHash the Hash of the Password of the Group (used for joining)
 */
class Group(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Group>(Groups)

    var name by Groups.name
    var handle by Groups.handle
    var ownerId by User referencedOn Groups.ownerId
    var passwordHash by Groups.passwordHash
}
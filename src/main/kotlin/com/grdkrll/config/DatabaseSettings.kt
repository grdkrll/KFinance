/**
 * This file contains basic configuration for connection to the database (by default uses MySQL)
 */
package com.grdkrll.config

import com.grdkrll.model.table.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

val config = HikariConfig().apply {
    jdbcUrl = "jdbc:mysql://localhost:3306/KFinance"
    driverClassName = "com.mysql.cj.jdbc.Driver"
    username = "root"
    password = "Passw0rd"
    maximumPoolSize = 10
}

fun configureDatabase() {
    val source = HikariDataSource(config)
    Database.connect(source)
    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Users, Groups, UsersGroups, MoneyTransactions, UsersTransactions)
    }
}

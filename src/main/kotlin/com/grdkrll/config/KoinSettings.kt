/**
 * This file contains basic configuration for dependency injection via Koin Framework
 */
package com.grdkrll.config

import com.grdkrll.controller.handler.GroupHandler
import com.grdkrll.controller.handler.MoneyTransactionHandler
import com.grdkrll.controller.handler.UserHandler
import com.grdkrll.controller.handler.impl.GroupHandlerImpl
import com.grdkrll.controller.handler.impl.MoneyTransactionHandlerImpl
import com.grdkrll.controller.handler.impl.UserHandlerImpl
import com.grdkrll.service.GroupService
import com.grdkrll.service.MoneyTransactionService
import com.grdkrll.service.UserService
import com.grdkrll.service.impl.GroupServiceImpl
import com.grdkrll.service.impl.MoneyTransactionServiceImpl
import com.grdkrll.service.impl.UserServiceImpl
import org.koin.dsl.module

object KoinSettings {
    val services = module {
        single<UserService> { UserServiceImpl() }
        single<GroupService> { GroupServiceImpl() }
        single<MoneyTransactionService> {MoneyTransactionServiceImpl()}
    }
    val handlers = module {
        single<UserHandler> { UserHandlerImpl() }
        single<GroupHandler> { GroupHandlerImpl() }
        single<MoneyTransactionHandler> { MoneyTransactionHandlerImpl() }
    }
}
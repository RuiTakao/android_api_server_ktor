package com.android.server.config

import io.ktor.server.config.ApplicationConfig

data class DbConfig(
    val driver: String,
    val url: String,
    val user: String,
    val password: String,
    val maxPoolSize: Int,
    val initializationFailTimeout : Long,
    val isAutoCommit: Boolean = false,
    val transactionIsolation: String = "TRANSACTION_REPEATABLE_READ",
)

fun provideDbConfig(config: ApplicationConfig): DbConfig {
    val c = config.config("db")
    return DbConfig(
        driver = c.property("driver").getString(),
        url = c.property("jdbcUrl").getString(),
        user = c.property("username").getString(),
        password = c.property("password").getString(),
        maxPoolSize = c.property("maximumPoolSize").getString().toInt(),
        initializationFailTimeout = c.property("initializationFailTimeout").getString().toLong(),
        isAutoCommit = c.propertyOrNull("isAutoCommit")?.getString()?.toBoolean() ?: false,
        transactionIsolation = c.propertyOrNull("transactionIsolation")?.getString() ?: "TRANSACTION_REPEATABLE_READ",
    )
}

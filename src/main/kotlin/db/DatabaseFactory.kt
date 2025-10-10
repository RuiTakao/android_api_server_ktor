package com.android.server.db

import com.android.server.config.DbConfig
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import javax.sql.DataSource

object DatabaseFactory {
    fun init(cfg: DbConfig) {
        val ds = hikari(cfg)
        Database.connect(ds)
    }

    private fun hikari(cfg: DbConfig): DataSource {
        val hikari = HikariConfig().apply {
            driverClassName = cfg.driver
            jdbcUrl = cfg.url
            username = cfg.user
            password = cfg.password
            maximumPoolSize = cfg.maxPoolSize
            isAutoCommit = cfg.isAutoCommit
            transactionIsolation = cfg.transactionIsolation

            initializationFailTimeout = cfg.initializationFailTimeout
            validate()
        }
        return HikariDataSource(hikari)
    }
}
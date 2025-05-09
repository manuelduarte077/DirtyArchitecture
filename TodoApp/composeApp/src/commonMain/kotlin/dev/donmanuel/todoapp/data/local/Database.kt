package dev.donmanuel.todoapp.data.local

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

/**
 * A wrapper class for configuring and building a Room database instance.
 *
 * @property builder A `RoomDatabase.Builder` instance used to configure the database.
 */
class Database(private val builder: RoomDatabase.Builder<RoomDB>) {

    /**
     * Builds and returns a configured instance of the Room database.
     *
     * The database is configured with the following options:
     * - `fallbackToDestructiveMigration`: Drops all tables and recreates them if a migration is missing.
     * - `setDriver`: Uses a bundled SQLite driver for database operations.
     * - `setQueryCoroutineContext`: Sets the coroutine context for database queries to `Dispatchers.IO`.
     *
     * @return A fully configured `RoomDB` instance.
     */
    fun getDatabase(): RoomDB {
        return builder
            .fallbackToDestructiveMigration(dropAllTables = true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}
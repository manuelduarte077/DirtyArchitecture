package dev.donmanuel.todoapp.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import dev.donmanuel.todoapp.data.local.dao.TodoDao
import dev.donmanuel.todoapp.data.model.TodoItem

@Database(
    entities = [TodoItem::class],
    version = 1,
    exportSchema = true
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class RoomDB : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<RoomDB> {
    override fun initialize(): RoomDB
}
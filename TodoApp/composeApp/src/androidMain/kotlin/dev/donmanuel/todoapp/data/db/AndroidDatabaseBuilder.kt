package dev.donmanuel.todoapp.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.donmanuel.todoapp.data.local.RoomDB

fun androidDatabaseBuilder(context: Context): RoomDatabase.Builder<RoomDB> {
    val dbFile = context.applicationContext.getDatabasePath("todo.db")

    return Room.databaseBuilder(
        context,
        dbFile.absolutePath
    )
}
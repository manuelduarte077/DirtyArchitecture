package dev.donmanuel.todoapp.data.db

import androidx.room.Room
import androidx.room.RoomDatabase
import dev.donmanuel.todoapp.data.local.RoomDB
import java.io.File

fun desktopDatabaseBuilder(): RoomDatabase.Builder<RoomDB> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "todo.db")

    return Room.databaseBuilder(
        dbFile.absolutePath,
    )
}
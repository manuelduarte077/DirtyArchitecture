package dev.donmanuel.todoapp.data.db

import androidx.room.Room
import androidx.room.RoomDatabase
import dev.donmanuel.todoapp.data.local.RoomDB
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

fun iosDatabaseBuilder(): RoomDatabase.Builder<RoomDB> {
    val dbFilePath = documentDirectory() + "/todo.db"

    return Room.databaseBuilder<RoomDB>(
        dbFilePath
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )

    return requireNotNull(documentDirectory?.path)
}
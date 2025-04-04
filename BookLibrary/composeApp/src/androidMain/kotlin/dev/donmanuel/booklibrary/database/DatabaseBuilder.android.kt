package dev.donmanuel.booklibrary.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.donmanuel.booklibrary.data.BookDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<BookDatabase> {
    val dbFile = context.getDatabasePath("book.db")
    return Room.databaseBuilder(
        context = context,
        name = dbFile.absolutePath
    )
}
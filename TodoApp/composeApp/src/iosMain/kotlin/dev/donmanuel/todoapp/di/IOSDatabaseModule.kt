package dev.donmanuel.todoapp.di

import androidx.room.RoomDatabase
import dev.donmanuel.todoapp.data.db.iosDatabaseBuilder
import dev.donmanuel.todoapp.data.local.RoomDB
import org.koin.dsl.module

val iosDatabaseModule = module {
    single<RoomDatabase.Builder<RoomDB>> { iosDatabaseBuilder() }
}
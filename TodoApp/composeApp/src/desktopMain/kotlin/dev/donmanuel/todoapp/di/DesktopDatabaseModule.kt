package dev.donmanuel.todoapp.di

import androidx.room.RoomDatabase
import dev.donmanuel.todoapp.data.db.desktopDatabaseBuilder
import dev.donmanuel.todoapp.data.local.RoomDB
import org.koin.dsl.module

val desktopDatabaseModule = module {
    single<RoomDatabase.Builder<RoomDB>> { desktopDatabaseBuilder() }
}
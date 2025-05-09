package dev.donmanuel.todoapp.di

import androidx.room.RoomDatabase
import dev.donmanuel.todoapp.data.db.androidDatabaseBuilder
import dev.donmanuel.todoapp.data.local.RoomDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidDatabaseModule = module {
    single<RoomDatabase.Builder<RoomDB>> { androidDatabaseBuilder(androidContext()) }
}
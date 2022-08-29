package com.example.cryptoapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class SharedPreferencesSession

@InstallIn(SingletonComponent::class)
@Module
class LoggingModule {
    @SharedPreferencesSession
    @Provides
    @Singleton
    fun provideSharedPrefSession(@ApplicationContext appContext: Context): SharedPreferences =
        appContext.getSharedPreferences(
            "session_id",
            Application.MODE_PRIVATE
        )
}
package com.example.xpense.ui

import android.app.Application
import androidx.room.Room
import com.example.xpense.data.ExpenseDB
import com.example.xpense.data.ExpenseRepositoryImpl
import com.example.xpense.data.ExpenseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesQuestionsCacheDB(app: Application): ExpenseDB {
        return Room.databaseBuilder(
            app,
            ExpenseDB::class.java,
            ExpenseDB.DATABASE_NAME
        ).build()

    }

    @Provides
    @Singleton
    fun providesTransactionRepository(
        db: ExpenseDB,
    ): ExpenseRepository = ExpenseRepositoryImpl(db.expenseDao)

}
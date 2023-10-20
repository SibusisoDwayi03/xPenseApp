package com.example.xpense.presentation

import android.app.Application
import androidx.room.Room
import com.example.xpense.data.TransactionsDB
import com.example.xpense.data.TransactionRepositoryImpl
import com.example.xpense.data.TransactionRepository
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
    fun providesQuestionsCacheDB(app: Application): TransactionsDB {
        return Room.databaseBuilder(
            app,
            TransactionsDB::class.java,
            TransactionsDB.DATABASE_NAME
        ).build()

    }

    @Provides
    @Singleton
    fun providesTransactionRepository(
        db: TransactionsDB,
    ): TransactionRepository = TransactionRepositoryImpl(db.transactionDao)

}
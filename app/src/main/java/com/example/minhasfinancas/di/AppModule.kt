package com.example.minhasfinancas.di

import android.app.Application
import androidx.room.Room
import com.example.minhasfinancas.data.FinancasDatabase
import com.example.minhasfinancas.data.ContasRepository
import com.example.minhasfinancas.data.ContasRepositoryImpl
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
    fun provideFinancasDatabase(app: Application): FinancasDatabase {
        return Room.databaseBuilder(
            app,
            FinancasDatabase::class.java,
            "financas_db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideContasRepository(db: FinancasDatabase):ContasRepository{
        return ContasRepositoryImpl(db.dao)
    }

}
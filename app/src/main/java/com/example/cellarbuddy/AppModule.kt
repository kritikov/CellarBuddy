package com.example.cellarbuddy

import android.content.Context
import com.example.cellarbuddy.data.dao.CategoryDao
import com.example.cellarbuddy.data.dao.DrinkDao
import com.example.cellarbuddy.data.dao.TypeDao
import com.example.cellarbuddy.data.db.AppDatabase
import com.example.cellarbuddy.data.repositories.CategoryRepository
import com.example.cellarbuddy.data.repositories.DrinkRepository
import com.example.cellarbuddy.data.repositories.TypeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.buildDatabase(context)
    }

    @Provides
    fun provideTypeDao(database: AppDatabase): TypeDao {
        return database.typeDao()
    }

    @Provides
    @Singleton
    fun provideTypeRepository(dao: TypeDao): TypeRepository {
        return TypeRepository(dao)
    }

    @Provides
    fun provideCategoryDao(database: AppDatabase): CategoryDao {
        return database.categoryDao()
    }
    @Provides
    @Singleton
    fun provideCategoryRepository(dao: CategoryDao): CategoryRepository {
        return CategoryRepository(dao)
    }

    @Provides
    fun provideDrinkDao(database: AppDatabase): DrinkDao {
        return database.drinkDao()
    }
    @Provides
    @Singleton
    fun provideDrinkRepository(dao: DrinkDao): DrinkRepository {
        return DrinkRepository(dao)
    }

}
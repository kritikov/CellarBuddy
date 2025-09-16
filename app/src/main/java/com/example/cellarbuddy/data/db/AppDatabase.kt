package com.example.cellarbuddy.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cellarbuddy.data.dao.CategoryDao
import com.example.cellarbuddy.data.dao.DrinkDao
import com.example.cellarbuddy.data.dao.TypeDao
import com.example.cellarbuddy.data.models.Category
import com.example.cellarbuddy.data.models.CategoryTypeCrossRef
import com.example.cellarbuddy.data.models.Drink
import com.example.cellarbuddy.data.models.DrinkTypeCrossRef
import com.example.cellarbuddy.data.models.Type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities =[
        Category::class,
        Type::class,
        Drink::class,
        DrinkTypeCrossRef::class,
        CategoryTypeCrossRef::class,
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun typeDao(): TypeDao
    abstract fun drinkDao(): DrinkDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun buildDatabase(context: Context): AppDatabase {
            lateinit var instance: AppDatabase
            instance =  Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "cellar-buddy-db"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {

                            // we will add the initial data here
                            InitialDataProvider.seedDatabase(instance)
                        }
                    }
                })
                .fallbackToDestructiveMigration(true)
                .build()
            INSTANCE = instance
            return instance
        }
    }
}
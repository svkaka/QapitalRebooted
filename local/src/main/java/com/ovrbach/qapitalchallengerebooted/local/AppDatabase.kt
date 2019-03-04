package com.ovrbach.qapitalchallengerebooted.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ovrbach.qapitalchallengerebooted.local.dao.GoalDao
import com.ovrbach.qapitalchallengerebooted.local.entity.GoalEntity

@Database(
    entities = [GoalEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun goals(): GoalDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = buildDatabase(context)
                    }
                    return INSTANCE!!
                }
            }
            return INSTANCE!!
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "database"
        ).build()
    }

}
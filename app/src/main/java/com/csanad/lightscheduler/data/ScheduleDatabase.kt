package com.csanad.lightscheduler.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.csanad.lightscheduler.util.Constants.LIGHT_SCHEDULER_DATABASE

@Database(entities = [ScheduleEntity::class], version = 1, exportSchema = false)
abstract class ScheduleDatabase : RoomDatabase() {

    abstract fun getScheduleDao(): ScheduleDao

    suspend fun insertSchedule(scheduleEntity: ScheduleEntity) {
        getScheduleDao().insertSchedule(scheduleEntity)
    }

    fun readAllSchedules(): LiveData<List<ScheduleEntity>> {
        return getScheduleDao().readAllSchedules()
    }

    suspend fun deleteSchedule(scheduleEntity: ScheduleEntity) {
        getScheduleDao().deleteSchedule(scheduleEntity)
    }

    companion object {
        @Volatile
        private lateinit var instance: ScheduleDatabase
        fun getInstance(context: Context): ScheduleDatabase {
            if (!this::instance.isInitialized) {
                synchronized(this) {
                    if (!this::instance.isInitialized) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            ScheduleDatabase::class.java,
                            LIGHT_SCHEDULER_DATABASE
                        ).build()
                    }
                }
            }
            return instance
        }
    }
}
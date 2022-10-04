package com.csanad.lightscheduler.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.csanad.lightscheduler.util.Constants.SCHEDULE_TABLE

@Dao
interface ScheduleDao {
    @Insert
    suspend fun insertSchedule(scheduleEntity: ScheduleEntity)

    @Query("SELECT * FROM $SCHEDULE_TABLE ORDER BY finish ASC")
    fun readAllSchedules(): LiveData<List<ScheduleEntity>>

    @Delete
    suspend fun deleteSchedule(scheduleEntity: ScheduleEntity)
}
package com.csanad.lightscheduler

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csanad.lightscheduler.data.ScheduleDatabase
import com.csanad.lightscheduler.data.ScheduleEntity
import kotlinx.coroutines.launch

class MainViewModel(context: Context) : ViewModel() {
    private val database = ScheduleDatabase.getInstance(context)

    fun insert(scheduleEntity: ScheduleEntity) {
        viewModelScope.launch {
            database.insertSchedule(scheduleEntity)
        }
    }

    fun schedules() = database.readAllSchedules()

    fun delete(scheduleEntity: ScheduleEntity) {
        viewModelScope.launch {
            database.deleteSchedule(scheduleEntity)
        }
    }
}
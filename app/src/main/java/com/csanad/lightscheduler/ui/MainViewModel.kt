package com.csanad.lightscheduler.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csanad.lightscheduler.data.ScheduleDatabase
import com.csanad.lightscheduler.data.ScheduleEntity
import com.csanad.lightscheduler.dialogs.delete.DeleteListener
import com.csanad.lightscheduler.ui.ScheduleRecyclerViewAdapter.ListProvider
import kotlinx.coroutines.launch

class MainViewModel(context: Context) : ViewModel(), ListProvider, DeleteListener {
    private val database = ScheduleDatabase.getInstance(context)

    val current = MutableLiveData<ScheduleEntity>()

    fun insert(scheduleEntity: ScheduleEntity) {
        viewModelScope.launch {
            database.insertSchedule(scheduleEntity)
        }
    }

    override fun schedules() = database.readAllSchedules()

    override fun prepDelete(scheduleEntity: ScheduleEntity) {
        current.value = scheduleEntity
    }

    override fun delete(scheduleEntity: ScheduleEntity) {
        viewModelScope.launch {
            database.deleteSchedule(scheduleEntity)
        }
    }

    override fun clear() {
        current.value = null
    }

    override fun turn(scheduleEntity: ScheduleEntity) {
        TODO("Not yet implemented")
    }
}
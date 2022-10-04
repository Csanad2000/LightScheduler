package com.csanad.lightscheduler.dialogs.delete

import com.csanad.lightscheduler.data.ScheduleEntity

interface DeleteListener {
    fun onDeletePositive(scheduleEntity: ScheduleEntity)
}
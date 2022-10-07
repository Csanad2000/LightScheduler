package com.csanad.lightscheduler.dialogs.delete

import com.csanad.lightscheduler.data.ScheduleEntity

interface DeleteListener {
    fun delete(scheduleEntity: ScheduleEntity)
    fun clear()
}
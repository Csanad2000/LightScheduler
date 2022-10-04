package com.csanad.lightscheduler.dialogs.create

import com.csanad.lightscheduler.data.ScheduleEntity

interface CreateListener {
    fun onCreatePositive(scheduleEntity: ScheduleEntity)
}
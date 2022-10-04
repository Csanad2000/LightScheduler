package com.csanad.lightscheduler.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.csanad.lightscheduler.util.Constants.SCHEDULE_TABLE

@Entity(tableName = SCHEDULE_TABLE)
data class ScheduleEntity(
    var start: Long,
    var finish: Long,
    var title: String = "",
    var active: Boolean = true,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
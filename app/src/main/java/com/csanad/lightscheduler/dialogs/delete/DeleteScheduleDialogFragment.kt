package com.csanad.lightscheduler.dialogs.delete

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.csanad.lightscheduler.data.ScheduleEntity

class DeleteScheduleDialogFragment(
    private val listener: DeleteListener,
    private val scheduleEntity: ScheduleEntity
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Permanently delete schedule?")
                .setPositiveButton("Yes") { dialog, id ->
                    positive()
                    dismiss()
                }
                .setNegativeButton("No") { dialog, id ->
                    dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("There must be an activity")
    }

    private fun positive() {
        listener.delete(scheduleEntity)
    }

    private fun finalize() {
        listener.clear()
    }
}
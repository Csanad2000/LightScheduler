package com.csanad.lightscheduler.dialogs.delete

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
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
                }
                .setNegativeButton("No") { dialog, id ->
                }
            builder.create()
        } ?: throw IllegalStateException("There must be an activity")
    }

    private fun positive() {
        listener.delete(scheduleEntity)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listener.clear()
    }
}
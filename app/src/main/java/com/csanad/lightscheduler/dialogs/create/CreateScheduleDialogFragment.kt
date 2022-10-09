package com.csanad.lightscheduler.dialogs.create

import android.app.Dialog
import android.os.Bundle
import android.app.AlertDialog
import android.view.View
import androidx.fragment.app.DialogFragment
import com.csanad.lightscheduler.R
import com.csanad.lightscheduler.data.ScheduleEntity
import com.google.android.material.textfield.TextInputLayout

//TODO disabled unless both times set
class CreateScheduleDialogFragment(private val listener: CreateListener) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it)
            //TODO might not need requireActivity
            val view = requireActivity().layoutInflater.inflate(R.layout.dialog_create, null)
            builder.setTitle(R.string.create_schedule)
                .setView(view)
                .setPositiveButton("Create") { dialog, id ->
                    positive(view)
                }
                .setNegativeButton("Cancel") { dialog, id ->
                }
            builder.create()
        } ?: throw IllegalStateException("There must be an activity")
    }

    private fun positive(view: View) {
        val from = view.findViewById<TextInputLayout>(R.id.textInputLayout).editText!!.text.toString()
        val to = view.findViewById<TextInputLayout>(R.id.textInputLayout2).editText!!.text.toString()
        val title = view.findViewById<TextInputLayout>(R.id.textInputLayout3).editText!!.text.toString()
        if (title.isBlank()) {
            listener.create(ScheduleEntity(from.toLong(), to.toLong()))
        } else {
            listener.create(ScheduleEntity(from.toLong(), to.toLong(), title))
        }
    }
}
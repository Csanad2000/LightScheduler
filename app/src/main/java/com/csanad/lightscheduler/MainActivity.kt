package com.csanad.lightscheduler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.csanad.lightscheduler.data.ScheduleEntity
import com.csanad.lightscheduler.databinding.ActivityMainBinding
import com.csanad.lightscheduler.dialogs.delete.DeleteListener
import com.csanad.lightscheduler.dialogs.delete.DeleteScheduleDialogFragment

class MainActivity : AppCompatActivity(),DeleteListener {
    private lateinit var _viewModel: MainViewModel
    private val viewModel: MainViewModel
        get() = _viewModel

    private lateinit var _binding: ActivityMainBinding
    private val binding: ActivityMainBinding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = ViewModelProvider(this, MainViewModelFactory(this))[MainViewModel::class.java]
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.face_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
                openCreateDialog()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun openCreateDialog() {}

    private fun openDeleteDialog(scheduleEntity: ScheduleEntity){
        DeleteScheduleDialogFragment(this,scheduleEntity).show(supportFragmentManager,"delete")
    }

    override fun onDeletePositive(scheduleEntity: ScheduleEntity) {
        viewModel.delete(scheduleEntity)
    }
}
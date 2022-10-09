package com.csanad.lightscheduler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.csanad.lightscheduler.R
import com.csanad.lightscheduler.data.ScheduleEntity
import com.csanad.lightscheduler.databinding.ActivityMainBinding
import com.csanad.lightscheduler.dialogs.create.CreateScheduleDialogFragment
import com.csanad.lightscheduler.dialogs.delete.DeleteScheduleDialogFragment

class MainActivity : AppCompatActivity() {
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
        binding.recycler.adapter = ScheduleRecyclerViewAdapter(viewModel)
        viewModel.current.observe(this) {
            if (it is ScheduleEntity) {
                openDeleteDialog(it)
            }
        }
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

    private fun openCreateDialog() {
        CreateScheduleDialogFragment(viewModel)
            .show(supportFragmentManager, "create")
    }

    private fun openDeleteDialog(scheduleEntity: ScheduleEntity) {
        DeleteScheduleDialogFragment(viewModel, scheduleEntity)
            .show(supportFragmentManager, "delete")
    }
}
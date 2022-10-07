package com.csanad.lightscheduler.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.csanad.lightscheduler.R
import com.csanad.lightscheduler.data.ScheduleEntity

class ScheduleRecyclerViewAdapter(private val provider: ListProvider) :
    Adapter<ScheduleRecyclerViewAdapter.MyViewHolder>(), Observer<List<ScheduleEntity>> {

    private lateinit var data: List<ScheduleEntity>

    override fun onChanged(t: List<ScheduleEntity>?) {
        data = t ?: listOf<ScheduleEntity>()
        notifyDataSetChanged()
    }

    init {
        provider.schedules().observeForever(this)
    }

    private fun finalize() {
        provider.schedules().removeObserver(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data[position]
        holder.itemView.findViewById<TextView>(R.id.sample).text = item.title
        holder.itemView.findViewById<ImageButton>(R.id.delete).setOnClickListener {
            onDelete(item)
        }
        if (!item.active) holder.itemView.findViewById<ImageButton>(R.id.turn)
            .setImageResource(R.drawable.ic_baseline_dark_mode_24)
        holder.itemView.findViewById<ImageButton>(R.id.turn).setOnClickListener {
            onTurn(item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun onDelete(item: ScheduleEntity) {
        provider.prepDelete(item)
    }

    private fun onTurn(item: ScheduleEntity) {
        provider.turn(item)
    }

    class MyViewHolder(itemView: View) : ViewHolder(itemView)

    interface ListProvider {
        fun schedules(): LiveData<List<ScheduleEntity>>
        fun prepDelete(scheduleEntity: ScheduleEntity)
        fun turn(scheduleEntity: ScheduleEntity)
    }
}
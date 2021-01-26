package com.jalmassi.mytasks

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*

//class inherits from RecyclerView.Adaptor
class TaskAdapter(private val tasks: MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>()
{
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_task,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return tasks.size;
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val curTask = tasks[position];
        holder.itemView.apply{
            tvTask.text = curTask.title
            cbDone.isChecked = curTask.isChecked
            toggleStrikeThrough(tvTask, curTask.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTask, isChecked)
                curTask.isChecked = !curTask.isChecked
            }
        }
    }

    fun addTask(task: Task){
        tasks.add(task)
        //tell adapter that item inserted (placed as last element in list)
        notifyItemInserted(tasks.size-1)
    }
    fun deleteDoneTasks(){
        tasks.removeAll { task ->
            task.isChecked
        }
        //update list
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTask: TextView, isChecked: Boolean){
        if(isChecked){
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else{
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}
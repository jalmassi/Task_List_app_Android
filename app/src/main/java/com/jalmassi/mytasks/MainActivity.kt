package com.jalmassi.mytasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //lateinit: promise to kotlin we will initialize later
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //pass empty list to task list so no tasks when app launches
        taskAdapter = TaskAdapter(mutableListOf())
        /*adapter assigned to recyclerview*/
        rvTaskList.adapter = taskAdapter
        rvTaskList.layoutManager = LinearLayoutManager(this)

        //button listeners activated
        btnAddTask.setOnClickListener {
            val taskTitle = etTask.text.toString()
            if(taskTitle.isNotEmpty()){
                val task = Task(taskTitle)
                taskAdapter.addTask(task)
                etTask.text.clear()
            }
        }
        btnDeleteDoneTasks.setOnClickListener {
            taskAdapter.deleteDoneTasks()
        }
    }
}
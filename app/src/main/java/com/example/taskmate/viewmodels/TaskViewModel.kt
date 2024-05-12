package com.example.taskmate.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Query
import com.example.taskmate.models.Task
import com.example.taskmate.repository.TaskRepository
import com.example.taskmate.utils.Resource
import com.example.taskmate.models.Priority

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository = TaskRepository(application)
    val taskStateFlow get() =  taskRepository.taskStateFlow
    val statusLiveData get() =  taskRepository.statusLiveData
    val sortByLiveData get() =  taskRepository.sortByLiveData

    fun setSortBy(sort:Pair<String,Boolean>){
        taskRepository.setSortBy(sort)
    }

    fun getTaskList(isAsc : Boolean, sortByName:String) {
        taskRepository.getTaskList(isAsc, sortByName)
    }

    fun insertTask(task: Task){
        taskRepository.insertTask(task)
    }

    fun deleteTask(task: Task) {
        taskRepository.deleteTask(task)
    }

    fun deleteTaskUsingId(taskId: String){
        taskRepository.deleteTaskUsingId(taskId)
    }

    fun updateTask(task: Task) {
        taskRepository.updateTask(task)
    }

    fun updateTaskPaticularField(taskId: String,title:String,description:String, priority:String) {
        taskRepository.updateTaskPaticularField(taskId, title, description, priority)
    }
    fun searchTaskList(query: String){
        taskRepository.searchTaskList(query)
    }

    fun parsePriorityString(priority: String): Priority {
        return when(priority) {
            "High Priority" -> Priority.High
            "Low Priority" -> Priority.Low
            "Medium Priority" -> Priority.Medium
            else -> Priority.Low
        }
    }
}
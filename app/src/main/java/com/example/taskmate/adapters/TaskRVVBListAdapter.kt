package com.example.taskmate.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmate.R
import com.example.taskmate.databinding.ViewTaskGridLayoutBinding
import com.example.taskmate.databinding.ViewTaskListLayoutBinding
import com.example.taskmate.models.Task
import java.text.SimpleDateFormat
import java.util.Locale


class TaskRVVBListAdapter(
    private val isList: MutableLiveData<Boolean>,
    private val deleteUpdateCallback: (type: String, position: Int, task: Task) -> Unit,
) :
    ListAdapter<Task,RecyclerView.ViewHolder>(DiffCallback()) {



    class ListTaskViewHolder(private val viewTaskListLayoutBinding: ViewTaskListLayoutBinding) :
        RecyclerView.ViewHolder(viewTaskListLayoutBinding.root) {

        fun bind(
            task: Task,
            deleteUpdateCallback: (type: String, position: Int, task: Task) -> Unit,
        ) {
            viewTaskListLayoutBinding.titleTxt.text = task.title
            viewTaskListLayoutBinding.descrTxt.text = task.description
            val dateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a", Locale.getDefault())
            viewTaskListLayoutBinding.dateTxt.text = dateFormat.format(task.date)

            // Dynamically change the background color of priority_indicator based on priority
            val priorityIndicator = getPriorityColor(viewTaskListLayoutBinding.root.context,task.priority)
            viewTaskListLayoutBinding.priorityIndicator.setCardBackgroundColor(priorityIndicator)

            viewTaskListLayoutBinding.deleteImg.setOnClickListener {
                if (adapterPosition != -1) {
                    deleteUpdateCallback("delete", adapterPosition, task)
                }
            }
            viewTaskListLayoutBinding.editImg.setOnClickListener {
                if (adapterPosition != -1) {
                    deleteUpdateCallback("update", adapterPosition, task)
                }
            }
        }

        private fun getPriorityColor(context: Context, priority: String): Int {
            return when (priority) {
                "High Priority" -> ContextCompat.getColor(context, R.color.Priority_High)
                "Medium Priority" -> ContextCompat.getColor(context, R.color.Priority_Medium)
                "Low Priority" -> ContextCompat.getColor(context, R.color.Priority_Low)
                else -> ContextCompat.getColor(context, R.color.Priority_null)
            }
        }
    }


    class GridTaskViewHolder(private val viewTaskGridLayoutBinding: ViewTaskGridLayoutBinding) :
        RecyclerView.ViewHolder(viewTaskGridLayoutBinding.root) {

        fun bind(
            task: Task,
            deleteUpdateCallback: (type: String, position: Int, task: Task) -> Unit,
        ) {
            viewTaskGridLayoutBinding.titleTxt.text = task.title
            viewTaskGridLayoutBinding.descrTxt.text = task.description

            val dateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a", Locale.getDefault())
            viewTaskGridLayoutBinding.dateTxt.text = dateFormat.format(task.date)

            // Dynamically change the background color of priority_indicator based on priority
            val priorityIndicator = getPriorityColor(viewTaskGridLayoutBinding.root.context,task.priority)
            viewTaskGridLayoutBinding.priorityIndicator.setCardBackgroundColor(priorityIndicator)

            viewTaskGridLayoutBinding.deleteImg.setOnClickListener {
                if (adapterPosition != -1) {
                    deleteUpdateCallback("delete", adapterPosition, task)
                }
            }
            viewTaskGridLayoutBinding.editImg.setOnClickListener {
                if (adapterPosition != -1) {
                    deleteUpdateCallback("update", adapterPosition, task)
                }
            }
        }

        private fun getPriorityColor(context: Context, priority: String): Int {
            return when (priority) {
                "High Priority" -> ContextCompat.getColor(context, R.color.Priority_High)
                "Medium Priority" -> ContextCompat.getColor(context, R.color.Priority_Medium)
                "Low Priority" -> ContextCompat.getColor(context, R.color.Priority_Low)
                else -> ContextCompat.getColor(context, R.color.Priority_null)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return if (viewType == 1){  // Grid_Item
            GridTaskViewHolder(
                ViewTaskGridLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }else{  // List_Item
            ListTaskViewHolder(
                ViewTaskListLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = getItem(position)

        if (isList.value!!){
            (holder as ListTaskViewHolder).bind(task,deleteUpdateCallback)
        }else{
            (holder as GridTaskViewHolder).bind(task,deleteUpdateCallback)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (isList.value!!){
            0 // List_Item
        }else{
            1 // Grid_Item
        }
    }



    class DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

    }




}
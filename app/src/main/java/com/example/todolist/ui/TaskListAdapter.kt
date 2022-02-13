package com.example.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ListTaskBinding
import com.example.todolist.model.Task

class TaskListAdapter: ListAdapter<Task, TaskListAdapter.TaskViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListTaskBinding.inflate(inflater, parent, false)
        return TaskViewholder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewholder, position: Int) {
        holder.bind(getItem(position))
    }


    class TaskViewholder(
        private val binding: ListTaskBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Task) {
            binding.tvTitle.text = item.title
            binding.tvSubt.text = "${item.date} ${item.hour}"
        }
    }
}

class DiffCallback: DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

}
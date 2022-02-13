package com.example.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.ListTaskBinding
import com.example.todolist.model.Task

class TaskListAdapter: ListAdapter<Task, TaskListAdapter.TaskViewholder>(DiffCallback()) {

    var listenerEdit: (Task) -> Unit = {}
    var listenerDelete: (Task) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListTaskBinding.inflate(inflater, parent, false)
        return TaskViewholder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewholder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class TaskViewholder(
        private val binding: ListTaskBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Task) {
            binding.tvTitle.text = item.title
            binding.tvSubt.text = "${item.date} ${item.hour}"
            binding.ivIcon.setOnClickListener {
                showPopUp(item)
            }
        }

        private fun showPopUp(item: Task) {
            val ivIcon = binding.ivIcon
            val popupMenu = PopupMenu(ivIcon.context, ivIcon)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_edit -> listenerEdit(item)
                    R.id.action_delete -> listenerDelete(item)
                }

                return@setOnMenuItemClickListener true
            }

            popupMenu.show()
        }
    }
}

class DiffCallback: DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

}
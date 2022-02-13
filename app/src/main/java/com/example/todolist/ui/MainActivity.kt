package com.example.todolist.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.datasource.TaskDataSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { TaskListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        insertLiestners()
    }

    //private val intent =


    private fun insertLiestners() {
        binding.btnAdd.setOnClickListener {
            resultLauncher.launch(Intent(this, AddTaskActivity::class.java))
        }

        /**  adapter.listenerEdit = {
           val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            resultLauncher.launch(intent)
        }*/

        adapter.listenerDelete = {
            Log.e("TAG", "listenerDelete: $it")
        }
    }


    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK){
            binding.rvList.adapter = adapter
            adapter.submitList(TaskDataSource.getList())
        }

    }

 /**   companion object{
        const val CREATE_NEW_TASK = 1000
    }*/

}



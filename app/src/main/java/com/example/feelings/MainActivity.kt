package com.example.feelings

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var feelingViewModel: FeelingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //Create instance of recycleview
        val recycleView = findViewById<RecyclerView>(R.id.recycleView)

        //Create instance of adapter
        val adapter = FeelingAdapter(this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)

        //Initialize ViewModel
        feelingViewModel = ViewModelProvider(this).get(feelingViewModel::class.java)

        feelingViewModel.allFeelings.observe(this, Observer {
            if(it.isNotEmpty()){
                adapter.setFeelingList(it)
            }
        })

        fab.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                val _mode = data?.getIntExtra(AddActivity.EXTRA_MOOD, 0)
                val _remark = data?.getStringExtra(AddActivity.EXTRA_REMARK)
                val feeling: Feeling = Feeling(
                    id=0,
                    mood = _mode!!,
                    created_at = System.currentTimeMillis(),
                    remarks = _remark!!
                )
                //Insert record into Database
                feelingViewModel.insert(feeling)
            }
        }
    }

    companion object{
        const val REQUEST_CODE = 1
    }


}

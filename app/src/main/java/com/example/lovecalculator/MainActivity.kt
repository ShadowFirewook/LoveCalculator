package com.example.lovecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.lovecalculator.databinding.ActivityMainBinding
import com.example.lovecalculator.remote.LoveModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewModel:LoveViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
    }

    private fun initClickers() {
        with(binding){
            calculateBtn.setOnClickListener {
                viewModel.liveModel(firstEd.text.toString(),secondEd.text.toString()).observe(this@MainActivity,
                    Observer { model ->
                        val intent = Intent(this@MainActivity,ResultActivity::class.java)
                        intent.putExtra("data",model!!)
                        startActivity(intent)
                    })
            }
        }
    }
}
package com.example.lovecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lovecalculator.databinding.ActivityResultBinding
import com.example.lovecalculator.remote.LoveModel

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.firstNameTv.text = intent.getStringExtra("first name")
        binding.secondNameTv.text = intent.getStringExtra("second name")
        binding.resultScoreTv.text = intent.getStringExtra("result")
        binding.resultTv.text = intent.getStringExtra("percentage")

    }
}
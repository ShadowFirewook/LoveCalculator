package com.example.lovecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
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
        binding.calculateBtn.setOnClickListener{
            App.api.calculateLove(binding.firstEd.text.toString(),binding.secondEd.text.toString()).enqueue(object : Callback<LoveModel>{
                override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                    if (response.isSuccessful){
                        val intent = Intent(this@MainActivity,ResultActivity::class.java)
                        intent.putExtra("first name",response.body()?.firstName)
                        intent.putExtra("second name",response.body()?.secondName)
                        intent.putExtra("percentage",response.body()?.percentage)
                        intent.putExtra("result",response.body()?.result)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                    Log.e("ololo","onFailure: ${t.message}")
                }
            })
        }

    }

    private fun initClickers() {
        with(binding){
            calculateBtn.setOnClickListener {
                viewModel.liveModel(firstEd.text.toString(),secondEd.text.toString()).observe(this@MainActivity,
                    Observer { model ->
                        App.appDatabase.loveDao().insert(model)
                        val bundle = Bundle()
                        bundle.putSerializable("love",model)

                    })
            }
        }
    }
}
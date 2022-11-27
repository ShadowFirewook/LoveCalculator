package com.example.lovecalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lovecalculator.remote.LoveModel

class LoveViewModel : ViewModel(){

    val repository = Repository()

    fun liveModel(firstName:String,secondName:String):LiveData<LoveModel>{
        return repository.getLiveLoveModel(firstName,secondName)
    }

}
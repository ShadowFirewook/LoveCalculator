package com.example.lovecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lovecalculator.databinding.FragmentHistoryBinding
import com.example.lovecalculator.remote.LoveModel

class HistoryFragment : Fragment() {
    lateinit var binding: FragmentHistoryBinding
    lateinit var loveModel:LoveModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root

        loveModel =arguments?. getSerializable("ключ") as LoveModel

        val list = App.appDatabase.loveDao().getAll()
        var result = ""
        list.forEach{
            result += "${it.firstName}\n${it.secondName}\n${it.percentage}\n${it.result} "
        }
        binding.historyTv.text = result
    }
}
package com.example.lovecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lovecalculator.databinding.FragmentResultBinding
import com.example.lovecalculator.remote.LoveModel

class ResultFragment : Fragment() {

    lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      /*  binding.firstNameTv.text = intent.getStringExtra("first name")
        binding.secondNameTv.text = intent.getStringExtra("second name")
        binding.resultScoreTv.text = intent.getStringExtra("result")
        binding.resultTv.text = intent.getStringExtra("percentage")*/
        val loveHistory =arguments?.getSerializable("love") as LoveModel
        binding.firstNameTv.text = loveHistory.firstName
        binding.secondNameTv.text = loveHistory.secondName
        binding.resultScoreTv.text = loveHistory.result
        binding.resultTv.text = loveHistory.percentage
        App.appDatabase.loveDao().insert(loveHistory)
        binding.loveCalculatorResultHistoryIv.setOnClickListener {
            findNavController().navigate(R.id.fragment_history)

        }
        binding.tryAgainBtn.setOnClickListener{
            findNavController().navigate(R.id.mainFragment)
        }
    }

}
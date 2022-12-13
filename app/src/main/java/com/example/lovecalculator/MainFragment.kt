package com.example.lovecalculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.lovecalculator.databinding.FragmentMainBinding
import com.example.lovecalculator.remote.LoveModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    val viewModel:LoveViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calculateBtn.setOnClickListener{
            onCalculateLove()
        }
        binding.loveCalculatorHistoryIv.setOnClickListener {
            findNavController().navigate(R.id.fragment_history)
        }
    }

    private fun onCalculateLove(){

        App.api.calculateLove(binding.firstEd.text.toString(),binding.secondEd.text.toString()).enqueue(object :
            Callback<LoveModel> {
            override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                if (response.isSuccessful){
                    /*val intent = Intent(requireContext(),ResultActivity::class.java)
                    intent.putExtra("first name",response.body()?.firstName)
                    intent.putExtra("second name",response.body()?.secondName)
                    intent.putExtra("percentage",response.body()?.percentage)
                    intent.putExtra("result",response.body()?.result)
                    response.body()?.let { HistoryCardsAdapter().addHistoryLove(it) }
                    startActivity(intent)*/
                    findNavController().navigate(R.id.resultFragment, bundleOf("love" to response.body()))


                }
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                Log.e("ololo","onFailure: ${t.message}")
            }
        })
    }

    private fun initClickers() {
        with(binding){
            calculateBtn.setOnClickListener {
                viewModel.liveModel(firstEd.text.toString(),secondEd.text.toString()).observe(requireActivity(),
                    Observer { model ->
                        App.appDatabase.loveDao().insert(model)
                        val bundle = Bundle()
                        bundle.putSerializable("love",model)
                        val transaction = requireActivity().supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.mainFragment,HistoryFragment())
                        transaction.disallowAddToBackStack()
                        transaction.commit()
                    })
            }
        }
    }
}
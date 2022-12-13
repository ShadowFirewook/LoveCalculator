package com.example.lovecalculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.lovecalculator.databinding.ItemHistoryLoveBinding
import com.example.lovecalculator.remote.LoveModel

class HistoryCardsAdapter:Adapter <HistoryCardsAdapter.HistoryCardsViewHolder>(){

    private val items = arrayListOf<LoveModel>()

    inner class HistoryCardsViewHolder(val binding: ItemHistoryLoveBinding): ViewHolder(binding.root){

        fun bind(loveModel: LoveModel){
            var item  = items[adapterPosition]
            binding.firstNameTv.text = item.firstName
            binding.secondNameTv.text = item.secondName
            binding.resultScoreTv.text = item.percentage
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryCardsViewHolder {
        return HistoryCardsViewHolder(ItemHistoryLoveBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HistoryCardsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addHistoryLove(loveModel: LoveModel){
        items.add(0,loveModel)
        notifyItemChanged(0)
    }
    fun addHistoryLoves(list: List<LoveModel>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

}
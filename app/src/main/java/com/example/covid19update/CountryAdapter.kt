package com.example.covid19update

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.country_list_item.view.*

class CountryAdapter(val list: List<Model>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.country_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: Model) {
            with(itemView) {
                tvCountryName.text = model.country
                Glide.with(this).load(model.flag).into(ivFlag)
            }
        }
    }
}

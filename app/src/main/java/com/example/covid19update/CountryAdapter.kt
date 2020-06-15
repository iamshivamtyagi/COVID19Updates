package com.example.covid19update

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.country_list_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class CountryAdapter(
    private var list: ArrayList<Model>
) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), Filterable {

    private var listFiltered = ArrayList<Model>()

    init {
        listFiltered = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.country_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = listFiltered.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(listFiltered[position])
    }

    override fun getFilter(): android.widget.Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty())
                    listFiltered = list
                else {
                    val resultList = ArrayList<Model>()
                    for (row in list) {
                        if (row.country.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    listFiltered = resultList
                }
                val filteredResult = FilterResults()
                filteredResult.values = listFiltered
                return filteredResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listFiltered = results?.values as ArrayList<Model>
                notifyDataSetChanged()
            }
        }
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

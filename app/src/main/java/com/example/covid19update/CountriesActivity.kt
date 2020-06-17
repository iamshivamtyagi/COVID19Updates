package com.example.covid19update

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_countries.*
import org.json.JSONArray

private const val url = "https://corona.lmao.ninja/v2/countries/"
var countryList: ArrayList<Model> = ArrayList()

class CountriesActivity : AppCompatActivity(), OnCountryClickListener {

    var adapter = CountryAdapter(countryList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
        setSupportActionBar(toolbar)

        // to set custom Title to current activity toolbar
        supportActionBar?.title = "Affected Countries"

        // to set back button in the activity
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        rvList.apply {
            layoutManager = LinearLayoutManager(this@CountriesActivity)
            adapter = this@CountriesActivity.adapter
        }

        fetchData()
    }

    private fun fetchData() {

        val request = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
            try {
                val jsonArray = JSONArray(response)

                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)

                    val countryName = jsonObject.getString("country")
                    val cases = jsonObject.getString("cases")
                    val todayCases = jsonObject.getString("todayCases")
                    val deaths = jsonObject.getString("deaths")
                    val todayDeaths = jsonObject.getString("todayDeaths")
                    val recovered = jsonObject.getString("recovered")
                    val active = jsonObject.getString("active")
                    val critical = jsonObject.getString("critical")
                    val casesPerMillion = jsonObject.getString("casesPerOneMillion")
                    val deathsPerMillion = jsonObject.getString("deathsPerOneMillion")
                    val activePerMillion = jsonObject.getString("activePerOneMillion")
                    val recoveredPerOneMillion = jsonObject.getString("recoveredPerOneMillion")
                    val todayRecovered = jsonObject.getString("todayRecovered")

                    val obj = jsonObject.getJSONObject("countryInfo")
                    val flagUrl = obj.getString("flag")

                    val countryModel = Model(
                        flagUrl,
                        countryName,
                        cases,
                        todayCases,
                        todayDeaths,
                        deaths,
                        recovered,
                        active,
                        critical,
                        casesPerMillion,
                        deathsPerMillion,
                        activePerMillion,
                        recoveredPerOneMillion,
                        todayRecovered
                    )
                    countryList.add(countryModel)
                }
                adapter = CountryAdapter(countryList, this)
                rvList.adapter = adapter
                progressBar.visibility = View.GONE
                rvList.visibility = View.VISIBLE
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(applicationContext, "Something went wrong !!!", Toast.LENGTH_SHORT)
                    .show()
                progressBar.visibility = View.GONE
                rvList.visibility = View.VISIBLE
            }
        }, Response.ErrorListener {
            progressBar.visibility = View.GONE
            rvList.visibility = View.VISIBLE
            Toast.makeText(this, "Something went wrong !!!", Toast.LENGTH_SHORT)
                .show()
        })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu?.findItem(R.id.search)
        val searchView = item?.actionView as androidx.appcompat.widget.SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            // finish activity when home/back icon pressed
            android.R.id.home -> {
                Toast.makeText(applicationContext, "Back To Home", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(item: Model, position: Int) {
        startActivity(
            Intent(
                applicationContext,
                CountryDetailActivity::class.java
            ).putExtra("position", position)
        )
    }

}

object GetPositionCountryModel {
     fun getPositionData(position: Int): Model {
        return countryList[position]
    }
    fun getCountryList(): ArrayList<Model> {
        return countryList
    }
}
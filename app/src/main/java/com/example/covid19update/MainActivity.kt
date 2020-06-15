package com.example.covid19update

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.eazegraph.lib.models.PieModel
import org.json.JSONObject

private const val url = "https://corona.lmao.ninja/v2/all/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        fetchData()

        btnTrack.setOnClickListener {
            startActivity(Intent(applicationContext, CountriesActivity::class.java))
        }
    }

    private fun fetchData() {

        val request = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->

            try {
                val jsonObject = JSONObject(response.toString())

                // populating Views with data receive from API
                tvCases.text = jsonObject.getString("cases")
                tvTodayCases.text = jsonObject.getString("todayCases")
                tvTotalDeaths.text = jsonObject.getString("deaths")
                tvTodayDeaths.text = jsonObject.getString("todayDeaths")
                tvRecovered.text = jsonObject.getString("recovered")
                tvTodayRecovered.text = jsonObject.getString("todayRecovered")
                tvActive.text = jsonObject.getString("active")
                tvCritical.text = jsonObject.getString("critical")
                tvCasesPerMillion.text = jsonObject.getString("casesPerOneMillion")
                tvDeathsPerMillion.text = jsonObject.getString("deathsPerOneMillion")
                tvActivePerMillion.text = jsonObject.getString("activePerOneMillion")
                tvRecoveredPerMillion.text = jsonObject.getString("recoveredPerOneMillion")
                tvAffectedCountries.text = jsonObject.getString("affectedCountries")

                // Updating the pie chart
                pieChart.addPieSlice(
                    PieModel(
                        "Active",
                        Integer.parseInt(tvActive.text.toString()).toFloat(),
                        Color.parseColor("#FFDD00")
                    )
                )
                pieChart.addPieSlice(
                    PieModel(
                        "Critical",
                        Integer.parseInt(tvCritical.text.toString()).toFloat(),
                        Color.parseColor("#FF0000")
                    )
                )
                pieChart.addPieSlice(
                    PieModel(
                        "Recovered",
                        Integer.parseInt(tvRecovered.text.toString()).toFloat(),
                        Color.parseColor("#69FF00")
                    )
                )
                pieChart.addPieSlice(
                    PieModel(
                        "Cases",
                        Integer.parseInt(tvTotalDeaths.text.toString()).toFloat(),
                        Color.parseColor("#000000")
                    )
                )

                pieChart.startAnimation()

                loader.visibility = View.GONE

                scrollStats.visibility = View.VISIBLE

            } catch (e: Exception) {
                e.printStackTrace()
            }


        },
            Response.ErrorListener {
                Toast.makeText(this, "Something went wrong !!!", Toast.LENGTH_SHORT)
                    .show()
            }
        )

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    }

}
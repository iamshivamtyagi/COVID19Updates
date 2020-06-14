package com.example.covid19update

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_countries.*
import kotlinx.android.synthetic.main.activity_main.*
import org.eazegraph.lib.models.PieModel
import org.json.JSONArray
import org.json.JSONObject

private const val url = "https://corona.lmao.ninja/v2/countries/"

class CountriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)

        setSupportActionBar(toolbar)

        fetchData()
    }

    private fun fetchData() {

        val request = StringRequest(
            Request.Method.GET, url, Response.Listener<String> { response ->
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

                        val obj = jsonObject.getJSONObject("countryInfo")
                        val flagUrl = obj.getString("flag")

                        val countryModel = Model(
                            flagUrl,
                            countryName,
                            cases,
                            todayCases,
                            deaths,
                            recovered,
                            active,
                            critical
                        )

                    }
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }
}
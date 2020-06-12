package com.example.covid19update

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

private const val url = "https://corona.lmao.ninja/v2/all/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()
    }

    private fun fetchData() {

        val request = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->

            try {
                val jsonObject = JSONObject(response.toString())

                tvCases.text = jsonObject.getString("cases")
                tvRecovered.text = jsonObject.getString("recovered")
                tvCritical.text = jsonObject.getString("critical")
                tvTotalDeaths.text = jsonObject.getString("deaths")
                tvActive.text = jsonObject.getString("active")
                tvNewCases.text = jsonObject.getString("todayCases")
                tvNewDeaths.text = jsonObject.getString("todayDeaths")
                tvAffectedCountries.text = jsonObject.getString("affectedCountries")


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
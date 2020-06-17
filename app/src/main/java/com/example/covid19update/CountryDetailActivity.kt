package com.example.covid19update

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_country_detail.*


class CountryDetailActivity : AppCompatActivity() {

    private var clickPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        supportActionBar?.title = "More Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val intent = intent
        clickPosition = intent.getIntExtra("position", 0)

        tvCases.text = getPositionCountryModel.getPositionData(clickPosition).cases
        tvRecovered.text = getPositionCountryModel.getPositionData(clickPosition).recovered
        tvCritical.text = getPositionCountryModel.getPositionData(clickPosition).critical
        tvTotalDeaths.text = getPositionCountryModel.getPositionData(clickPosition).deaths
        tvActive.text = getPositionCountryModel.getPositionData(clickPosition).active
        tvTodayCases.text = getPositionCountryModel.getPositionData(clickPosition).todayCases
        tvTodayDeaths.text = getPositionCountryModel.getPositionData(clickPosition).todayDeaths
        tvTodayRecovered.text =
            getPositionCountryModel.getPositionData(clickPosition).todayRecovered
        tvCasesPerMillion.text =
            getPositionCountryModel.getPositionData(clickPosition).casesPerMillion
        tvDeathsPerMillion.text =
            getPositionCountryModel.getPositionData(clickPosition).deathsPerMillion
        tvActivePerMillion.text =
            getPositionCountryModel.getPositionData(clickPosition).activePerMillion
        tvRecoveredPerMillion.text =
            getPositionCountryModel.getPositionData(clickPosition).recoveredPerMillion
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
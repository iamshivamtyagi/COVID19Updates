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
        setSupportActionBar(toolbarDetail)

        supportActionBar?.title = "More Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val intent = intent
        clickPosition = intent.getIntExtra("position", 0)

        tvCases.text = GetPositionCountryModel.getPositionData(clickPosition).cases
        tvRecovered.text = GetPositionCountryModel.getPositionData(clickPosition).recovered
        tvCritical.text = GetPositionCountryModel.getPositionData(clickPosition).critical
        tvTotalDeaths.text = GetPositionCountryModel.getPositionData(clickPosition).deaths
        tvActive.text = GetPositionCountryModel.getPositionData(clickPosition).active
        tvTodayCases.text = GetPositionCountryModel.getPositionData(clickPosition).todayCases
        tvTodayDeaths.text = GetPositionCountryModel.getPositionData(clickPosition).todayDeaths
        tvTodayRecovered.text =
            GetPositionCountryModel.getPositionData(clickPosition).todayRecovered
        tvCasesPerMillion.text =
            GetPositionCountryModel.getPositionData(clickPosition).casesPerMillion
        tvDeathsPerMillion.text =
            GetPositionCountryModel.getPositionData(clickPosition).deathsPerMillion
        tvActivePerMillion.text =
            GetPositionCountryModel.getPositionData(clickPosition).activePerMillion
        tvRecoveredPerMillion.text =
            GetPositionCountryModel.getPositionData(clickPosition).recoveredPerMillion
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
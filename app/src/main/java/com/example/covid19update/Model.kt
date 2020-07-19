package com.example.covid19update

data class Model(
    val flag: String,
    val country: String,
    val cases: String,
    val todayCases: String,
    val todayDeaths: String,
    val deaths: String,
    val recovered: String,
    val active: String,
    val critical: String,
    val casesPerMillion: String,
    val deathsPerMillion: String,
    val activePerMillion: String,
    val recoveredPerMillion: String,
    val todayRecovered: String
)

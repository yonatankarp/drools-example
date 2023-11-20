package com.example.drools.model

data class RiskRequest(
    val countryCode: String,
    val customerNumber: String,
    val email: String,
)
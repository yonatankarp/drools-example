package com.example.drools.rules

import com.example.drools.model.RiskRequest

interface Rule {
    fun invoke(request: RiskRequest)
}
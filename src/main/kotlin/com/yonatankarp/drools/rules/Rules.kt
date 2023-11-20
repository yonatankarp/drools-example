package com.yonatankarp.drools.rules

import com.yonatankarp.drools.model.RiskRequest

interface Rule {
    fun invoke(request: RiskRequest)
}
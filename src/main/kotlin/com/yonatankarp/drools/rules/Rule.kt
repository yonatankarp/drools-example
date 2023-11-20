package com.yonatankarp.drools.rules

import com.yonatankarp.drools.model.RiskRequest

interface Rule {
    suspend operator fun invoke(request: RiskRequest)
}

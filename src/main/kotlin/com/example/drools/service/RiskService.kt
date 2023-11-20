package com.example.drools.service

import com.example.drools.model.RiskRequest
import com.example.drools.rules.Rule
import org.kie.api.runtime.KieContainer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class RiskService(
    private val kieContainer: KieContainer,
    @Qualifier("rules-by-name") private val rules: Map<String, Rule>
) {
    fun getDiscount(riskRequest: RiskRequest) : Boolean {
        val ruleNames = mutableListOf<String>()
        kieContainer.newKieSession()
            .apply {
                setGlobal("ruleNames", ruleNames)
                insert(riskRequest)
                fireAllRules()
                dispose()
            }

        return if(ruleNames.isEmpty()) false
        else {
            ruleNames.forEach { rules[it]?.invoke(request = riskRequest) }
            return true
        }
    }
}
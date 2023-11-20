package com.yonatankarp.drools.service

import com.yonatankarp.drools.model.RiskRequest
import com.yonatankarp.drools.rules.Rule
import org.kie.api.runtime.KieContainer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class RiskService(
    private val kieContainer: KieContainer,
    @Qualifier("rulesByName") private val rules: Map<String, Rule>
) {
    suspend fun runRules(riskRequest: RiskRequest): Boolean {
        val ruleNames = getRulesNames(riskRequest)
        return ruleNames
            .takeIf { it.isNotEmpty() }
            ?.let { executeRules(riskRequest, ruleNames) }
            ?.let { true }
            ?: false
    }

    private suspend fun getRulesNames(riskRequest: RiskRequest): List<String> =
        mutableListOf<String>()
            .also {
                kieContainer.newKieSession()
                    .apply {
                        setGlobal("ruleNames", it)
                        insert(riskRequest)
                        fireAllRules()
                        dispose()
                    }
            }

    private suspend fun executeRules(riskRequest: RiskRequest, ruleNames: List<String>) =
        ruleNames.forEach {
            rules[it.lowercase()]?.let {
                it(request = riskRequest)
            }
        }
}
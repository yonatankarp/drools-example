package com.yonatankarp.drools.config

import org.kie.api.KieServices
import org.kie.api.runtime.KieContainer
import org.kie.internal.io.ResourceFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DroolsConfig {
    private val RULES_CUSTOMER_RULES_DRL = "rules/risk-rules.drl"
    private val kieServices = KieServices.Factory.get()

    @Bean
    fun kieContainer(): KieContainer {
        val kieFileSystem = kieServices.newKieFileSystem()
            .apply {
                write(ResourceFactory.newClassPathResource(RULES_CUSTOMER_RULES_DRL))
            }

        val kb = kieServices.newKieBuilder(kieFileSystem)
            .apply {
                buildAll()
            }

        return kieServices.newKieContainer(kb.kieModule.releaseId)
    }
}
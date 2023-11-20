package com.example.drools.config

import com.example.drools.rules.Rule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RulesConfig {
    @Bean(name = ["rules-by-name"])
    fun rulesMap(rules: List<Rule>): Map<String, Rule> =
        rules.associateBy { it::class.simpleName.toUpperCamelCase() }

    private fun String?.toUpperCamelCase() =
        this?.replaceFirstChar { it.uppercase() } ?: ""
}
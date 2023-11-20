package com.yonatankarp.drools.config

import com.yonatankarp.drools.rules.Rule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RulesConfig {
    @Bean(name = ["rules-by-name"])
    fun rulesMap(rules: List<Rule>): Map<String, Rule> =
        rules.associateBy { it::class.simpleName?.lowercase() ?: "" }
}
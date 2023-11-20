package com.example.drools.rules

import com.example.drools.model.RiskRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class EmailRule : Rule {
    override fun invoke(request: RiskRequest) {
        logger.info("Age in request is: ${request.email}")
    }

    companion object {
        val logger = LoggerFactory.getLogger(EmailRule::class.java)
    }
}
package com.yonatankarp.drools.rules

import com.yonatankarp.drools.model.RiskRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class EmailRule : Rule {
    override suspend fun invoke(request: RiskRequest) {
        logger.info("Age in request is: ${request.email}")
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(EmailRule::class.java)
    }
}
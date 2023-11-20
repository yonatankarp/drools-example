package com.yonatankarp.drools.rules

import com.yonatankarp.drools.model.RiskRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CustomerNumberRule: Rule {
    override fun invoke(request: RiskRequest) {
        logger.info("Customer number in request is: ${request.customerNumber}")
    }

    companion object {
        val logger = LoggerFactory.getLogger(CustomerNumberRule::class.java)
    }
}
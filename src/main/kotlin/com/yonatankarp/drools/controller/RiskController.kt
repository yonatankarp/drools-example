package com.yonatankarp.drools.controller

import com.yonatankarp.drools.model.RiskRequest
import com.yonatankarp.drools.model.RiskResponse
import com.yonatankarp.drools.service.RiskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RiskController(private val riskService: RiskService) {
    @PostMapping("/risk")
    suspend fun runRules(
        @RequestBody riskRequest: RiskRequest,
    ): ResponseEntity<RiskResponse> =
        riskService.runRules(riskRequest)
            .let { ResponseEntity.ok(it.toResponse()) }

    private suspend fun Boolean.toResponse() = RiskResponse(isRuleApplied = this)
}

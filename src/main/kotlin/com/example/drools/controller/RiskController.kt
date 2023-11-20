package com.example.drools.controller

import com.example.drools.model.RiskRequest
import com.example.drools.service.RiskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RiskController(private val riskService: RiskService) {
    @PostMapping("/get-discount")
    fun getDiscount(@RequestBody riskRequest: RiskRequest) =
        riskService.getDiscount(riskRequest)
            .let { ResponseEntity.ok(it) }
}
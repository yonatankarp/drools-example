package com.example.drools.controller

import com.example.drools.model.OrderRequest
import com.example.drools.service.OrderDiscountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderDiscountController(private val orderDiscountService: OrderDiscountService) {
    @PostMapping("/get-discount")
    fun getDiscount(@RequestBody orderRequest: OrderRequest) =
        orderDiscountService.getDiscount(orderRequest)
            .let { ResponseEntity.ok(it) }
}
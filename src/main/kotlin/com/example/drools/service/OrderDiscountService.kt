package com.example.drools.service

import com.example.drools.model.OrderDiscount
import com.example.drools.model.OrderRequest
import org.kie.api.runtime.KieContainer
import org.springframework.stereotype.Service

@Service
class OrderDiscountService(private val kieContainer: KieContainer) {
    fun getDiscount(orderRequest: OrderRequest): OrderDiscount {
        val orderDiscount = OrderDiscount()
        kieContainer.newKieSession()
            .apply {
                setGlobal("orderDiscount", orderDiscount)
                insert(orderRequest)
                fireAllRules()
                dispose()
            }
        return orderDiscount
    }
}
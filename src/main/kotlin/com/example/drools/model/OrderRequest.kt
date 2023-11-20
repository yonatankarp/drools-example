package com.example.drools.model

data class OrderRequest(
    val customerNumber: String,
    val age: Int,
    val amount: Int,
    val customerType: CustomerType
)

enum class CustomerType(val value: String) {
    LOYAL("Loyal"),
    NEW("New"),
    DISSATISFIED("Dissatisfied");
}
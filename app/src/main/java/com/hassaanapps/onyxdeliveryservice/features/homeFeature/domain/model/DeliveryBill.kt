package com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model

data class DeliveryBill(
    val billAmount: Float?,
    val billDate: String?,
    val billNumber: String?,
    val billSerial: String?,
    val billTime: String?,
    val billType: String?,
    val customerAddress: CustomerAddress,
    val customerName: String?,
    val deliveryAmount: String?,
    val deliveryStatusFlag: DeliveryBillStatus?,
    val latitude: String?,
    val longitude: String?,
    val mobileNumber: String?,
    val regionName: String?,
    val taxAmount: String?
)

enum class DeliveryBillStatus {
    NEW {
        override fun toString(): String = "New"
    },
    DELIVERED {
        override fun toString(): String = "Delivered"

    },
    UNDER_DELIVERING {
        override fun toString() = "Delivering"
    },
    RETURNED {
        override fun toString(): String = "Returned"
    },
}
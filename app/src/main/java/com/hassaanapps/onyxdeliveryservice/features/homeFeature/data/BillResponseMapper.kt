package com.hassaanapps.onyxdeliveryservice.features.homeFeature.data

import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.model.DeliveryBillsResponseItem
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.CustomerAddress
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.DeliveryBill
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.DeliveryBillStatus

fun DeliveryBillsResponseItem.mapToDomain(): DeliveryBill = DeliveryBill(
    billAmount = this.billAmount,
    billDate = this.billDate,
    billNumber = this.billNumber,
    billSerial = this.billSerial,
    billTime = this.billTime,
    billType = this.billType,
    customerAddress = CustomerAddress(
        customerAddress = this.customerAddress,
        customerApartmentNumber = this.customerApartmentNumber,
        customerBuildNumber = this.customerBuildNumber,
        customerFloorNumber = this.customerFloorNumber
    ),
    customerName = this.customerName,
    deliveryAmount = this.deliveryAmount,
    deliveryStatusFlag = mapToDeliveryState(this.deliveryStatusFlag),
    latitude = this.latitude,
    longitude = this.longitude,
    mobileNumber = this.mobileNumber,
    regionName = this.regionName,
    taxAmount = this.taxAmount
)

fun mapToDeliveryState(status: String?): DeliveryBillStatus? {
    return when (status) {
        "0" -> DeliveryBillStatus.New
        "1" -> DeliveryBillStatus.Delivered
        "2" -> DeliveryBillStatus.UNDER_DELIVERING
        "3" -> DeliveryBillStatus.RETURNED
        else -> null
    }

}
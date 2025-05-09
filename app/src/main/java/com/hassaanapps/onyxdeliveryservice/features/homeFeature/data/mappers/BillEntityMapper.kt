package com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.mappers

import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.local.entity.DeliveryBillEntity
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.CustomerAddress
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.DeliveryBill
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.domain.model.DeliveryBillStatus

fun DeliveryBill.mapToEntity() =
    DeliveryBillEntity(
        billNumber = this.billNumber ?: "",
        billAmount = this.billAmount,
        billDate = this.billDate,
        billSerial = this.billSerial,
        billTime = this.billTime,
        billType = this.billType,
        customerAddress = this.customerAddress.customerAddress,
        customerApartmentNumber = this.customerAddress.customerApartmentNumber,
        customerBuildNumber = this.customerAddress.customerBuildNumber,
        customerFloorNumber = this.customerAddress.customerFloorNumber,
        customerName = this.customerName,
        deliveryAmount = this.deliveryAmount,
        deliveryStatusFlag = this.deliveryStatusFlag?.mapToEntityString(),
        latitude = this.latitude,
        longitude = this.longitude,
        mobileNumber = this.mobileNumber,
        regionName = this.regionName,
        taxAmount = this.taxAmount
    )


fun DeliveryBillStatus.mapToEntityString(): String {
    return when (this) {
        DeliveryBillStatus.NEW -> "0"
        DeliveryBillStatus.DELIVERED -> "1"
        DeliveryBillStatus.UNDER_DELIVERING -> "2"
        DeliveryBillStatus.RETURNED -> "3"
    }
}


fun DeliveryBillEntity.mapToDomain() = DeliveryBill(
    billNumber = this.billNumber,
    billAmount = this.billAmount,
    billDate = this.billDate,
    billSerial = this.billSerial,
    billTime = this.billTime,
    billType = this.billType,
    customerAddress = CustomerAddress(
        customerAddress = this.customerAddress,
        customerApartmentNumber = this.customerApartmentNumber,
        customerBuildNumber = this.customerBuildNumber,
        customerFloorNumber = this.customerFloorNumber,
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
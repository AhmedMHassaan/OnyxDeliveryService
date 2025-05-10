package com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bills")
data class DeliveryBillEntity(
    @PrimaryKey(autoGenerate = false)
    val billSerial: String,
    val billNumber: String?,
    val billAmount: Float?,
    val billDate: String?,
    val billTime: String?,
    val billType: String?,
    val customerAddress: String?,
    val customerApartmentNumber: String?,
    val customerBuildNumber: String?,
    val customerFloorNumber: String?,
    val customerName: String?,
    val deliveryAmount: String?,
    val deliveryStatusFlag: String?,
    val latitude: String?,
    val longitude: String?,
    val mobileNumber: String?,
    val regionName: String?,
    val taxAmount: String?
)
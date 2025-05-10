package com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.local.dao.DeliveryBillsDao
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.local.entity.DeliveryBillEntity

@Database(entities = [DeliveryBillEntity::class], version = 2)
abstract class OnyxServiceDatabase : RoomDatabase() {
    abstract fun billsDao(): DeliveryBillsDao
}
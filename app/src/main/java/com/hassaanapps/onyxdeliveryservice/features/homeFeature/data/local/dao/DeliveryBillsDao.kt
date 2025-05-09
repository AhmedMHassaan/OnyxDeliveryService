package com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.local.entity.DeliveryBillEntity

@Dao
interface DeliveryBillsDao {

    @Insert(entity = DeliveryBillEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheAllBills(entities: List<DeliveryBillEntity>)


    @Query("SELECT * FROM bills WHERE deliveryStatusFlag = '0'")
    suspend fun getNewBills(): List<DeliveryBillEntity>

    @Query("SELECT * FROM bills WHERE deliveryStatusFlag != '0'")
    suspend fun getOthersBills(): List<DeliveryBillEntity>

}
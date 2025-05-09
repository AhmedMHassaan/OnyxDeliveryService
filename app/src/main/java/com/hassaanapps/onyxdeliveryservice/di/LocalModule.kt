package com.hassaanapps.onyxdeliveryservice.di

import androidx.room.Room
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.local.dao.DeliveryBillsDao
import com.hassaanapps.onyxdeliveryservice.features.homeFeature.data.local.db.OnyxServiceDatabase
import org.koin.dsl.module

val localModule = module {

    single<OnyxServiceDatabase> {
        Room.databaseBuilder(
            get(),
            OnyxServiceDatabase::class.java, "database-name"
        ).build()
    }

    single<DeliveryBillsDao> {
        (get() as OnyxServiceDatabase).billsDao()
    }
    
}
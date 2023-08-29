package com.example.billz.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.billz.model.Bill


@Database(entities = [Bill::class], version = 1)
abstract class BillsDb: RoomDatabase() {
    abstract fun billsDao(): BillsDao
    companion object{
        var database: BillsDb? = null

        fun  getDatabase(context: Context): BillsDb{
            if (database==null){
                database = Room.databaseBuilder(context, BillsDb::class.java, "BillzDb")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return  database as BillsDb
        }

    }
}
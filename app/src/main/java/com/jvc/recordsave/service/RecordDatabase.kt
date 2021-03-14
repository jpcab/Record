package com.jvc.recordsave.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jvc.recordsave.repository.model.Record
import com.jvc.recordsave.service.dao.RecordDao

/**
 * Created by Jp Cabrera on 3/12/2021.
 */

@Database(
    entities = [Record::class],
    version = 1,
    exportSchema = false
)
abstract class RecordDatabase : RoomDatabase() {

    abstract fun record(): RecordDao

    companion object {
        @Volatile
        private var INSTANCE: RecordDatabase? = null

        fun getDatabase(context: Context): RecordDatabase {
            if (INSTANCE != null) {
                return INSTANCE!!
            }
            synchronized(RecordDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    RecordDatabase::class.java,
                    "recordDB"
                ).build()
            }

            return INSTANCE!!
        }

        fun nukeDatabase() {
            INSTANCE!!.clearAllTables()

            INSTANCE = null
        }
    }
}
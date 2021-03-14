package com.jvc.recordsave.service.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.jvc.recordsave.repository.model.Record

@Dao
interface RecordDao : BaseDao<Record> {

    @Query("Select * from record_table")
    fun getRecords(): LiveData<List<Record>>

    @Query("SELECT * FROM record_table WHERE name LIKE :name")
    fun findByName(name: String): Record
}
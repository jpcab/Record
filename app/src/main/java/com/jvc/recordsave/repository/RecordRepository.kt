package com.jvc.recordsave.repository

import androidx.lifecycle.LiveData
import com.jvc.recordsave.repository.model.Record
import com.jvc.recordsave.service.dao.RecordDao

/**
 * Created by Jp Cabrera on 3/12/2021.
 */
class RecordRepository(val recordDao: RecordDao) {

    suspend fun insert(record: Record) {
        recordDao.insert(record)
    }

    suspend fun update(record: Record) {
        recordDao.update(record)
    }

    suspend fun delete(record: Record) {
        recordDao.delete(record)
    }

    private val records: LiveData<List<Record>> = recordDao.getRecords()
    fun getRecords(): LiveData<List<Record>> = records
}
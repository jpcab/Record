package com.jvc.recordsave.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jvc.recordsave.repository.RecordRepository
import com.jvc.recordsave.repository.model.Record
import com.jvc.recordsave.service.RecordDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Jp Cabrera on 3/12/2021.
 */


class MainViewModel(var _application: Application) : AndroidViewModel(_application) {

    private var recordRepository =
        RecordRepository(RecordDatabase.getDatabase(_application).record())

    val records: LiveData<List<Record>>

    init {
        records = recordRepository.getRecords()
    }

    fun delete(record: Record) = viewModelScope.launch(Dispatchers.IO) {
        recordRepository.delete(record)
    }

}
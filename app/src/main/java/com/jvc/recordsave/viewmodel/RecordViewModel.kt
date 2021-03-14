package com.jvc.recordsave.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.jvc.recordsave.R
import com.jvc.recordsave.repository.RecordRepository
import com.jvc.recordsave.repository.model.Record
import com.jvc.recordsave.service.RecordDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecordViewModel(var _application: Application) : AndroidViewModel(_application) {

    private val recordRepository: RecordRepository =
        RecordRepository(RecordDatabase.getDatabase(_application).record())
    var errorName: MutableLiveData<String> = MutableLiveData()
    var errorAddress: MutableLiveData<String> = MutableLiveData()
    var name: MutableLiveData<String> = MutableLiveData()
    var address: MutableLiveData<String> = MutableLiveData()
    var finishActivity: MutableLiveData<Record> = MutableLiveData()


    init {
        name.value = ""
        address.value = ""
        errorName.value = ""
        errorAddress.value = ""
    }

    fun onAddRecordClicked() {

        var name = name.value
        var address = address.value

        if (name!!.isEmpty()) {
            errorName.postValue(_application.getString(R.string.error_name))
        } else {
            errorName.postValue("")
        }

        if (address!!.isEmpty()) {
            errorAddress.postValue(_application.getString(R.string.error_address))
        } else {
            errorAddress.postValue("")
        }

        if (name.isNullOrEmpty() || address.isNullOrEmpty()) {
            return
        }

        finishActivity.postValue(Record(name = name, address = address))
    }

    fun insert(record: Record) = viewModelScope.launch(Dispatchers.IO) {
        recordRepository.insert(record)
    }

}
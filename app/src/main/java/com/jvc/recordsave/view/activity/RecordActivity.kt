package com.jvc.recordsave.view.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jvc.recordsave.R
import com.jvc.recordsave.databinding.ActivityRecordBinding
import com.jvc.recordsave.view.BaseActivity
import com.jvc.recordsave.viewmodel.RecordViewModel

class RecordActivity : BaseActivity() {

    private lateinit var recordViewModel: RecordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        provideViewModel()
        provideToolbar()

        recordViewModel.finishActivity.observe(this, Observer {
            recordViewModel.insert(it)
            finish()
        })
    }

    private fun provideViewModel() {
        recordViewModel = ViewModelProvider(this).get(RecordViewModel::class.java)
        val binding: ActivityRecordBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_record)
        binding.recordViewModel = recordViewModel
        binding.lifecycleOwner = this
    }

    fun cancelAddRecord(view: View) {
        finish()
    }
}

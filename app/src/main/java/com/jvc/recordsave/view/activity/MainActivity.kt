package com.jvc.recordsave.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jvc.recordsave.view.CustomDialog
import com.jvc.recordsave.view.CustomDialog.CustomDialogCallback
import com.jvc.recordsave.R
import com.jvc.recordsave.R.layout.activity_main
import com.jvc.recordsave.databinding.ActivityMainBinding
import com.jvc.recordsave.view.BaseActivity
import com.jvc.recordsave.view.adapter.RecordAdapter
import com.jvc.recordsave.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity() {

    private val addItems = arrayOf("Add Record", "Compare Record")
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        provideViewModel()
        provideRecyclerView()
        setSupportActionBar(toolbar)
    }

    private fun provideViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, activity_main)
        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = this
    }

    private fun provideRecyclerView() {
        val adapter = RecordAdapter()
        rc_record.setHasFixedSize(true)
        rc_record.layoutManager = LinearLayoutManager(this)
        rc_record.adapter = adapter

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mainViewModel.delete(adapter.getItem(viewHolder.adapterPosition))
                Toast.makeText(this@MainActivity, "Record deleted", Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(rc_record)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_item -> {
                CustomDialog.getDialogWithList(
                    this,
                    getString(R.string.dialog_list_title),
                    addItems,
                    object : CustomDialogCallback {
                        override fun onDialogClickListener(position: Int) {
                            when (position) {
                                0 -> {
                                    val intent = Intent(context, RecordActivity::class.java)
                                    startActivity(intent)
                                }
                                1 -> {
                                    Toast.makeText(
                                        context,
                                        getString(R.string.feature_not_available),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    })
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

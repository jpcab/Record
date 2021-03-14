package com.jvc.recordsave.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jvc.recordsave.R
import com.jvc.recordsave.repository.model.Record
import com.jvc.recordsave.view.BindableAdapter
import kotlinx.android.synthetic.main.row_record.view.*

class RecordAdapter : RecyclerView.Adapter<RecordAdapter.ViewHolder>(),
    BindableAdapter<List<Record>> {

    private var items: List<Record> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_record,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = items[position].name
    }

    override fun setData(data: List<Record>) {
        this.items = data
        notifyDataSetChanged()
    }

    fun getItem(pos: Int): Record {
        return items[pos]
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.name
    }
}
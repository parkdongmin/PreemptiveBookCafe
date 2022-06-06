package com.bignerdranch.android.preemptivebookcafeuser

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ReportHistoryListAdapter (private val itemList : List<ReportHistoryData>) :
    RecyclerView.Adapter<ReportHistoryViewHolder>() {

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportHistoryViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_report_history, parent, false)
        return ReportHistoryViewHolder(inflatedView);
    }

    override fun onBindViewHolder(holder: ReportHistoryViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            reportbind(item)
        }
    }
}
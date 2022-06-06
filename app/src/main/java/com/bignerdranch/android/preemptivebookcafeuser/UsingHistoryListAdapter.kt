package com.bignerdranch.android.preemptivebookcafeuser

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UsingHistoryListAdapter (private val itemList : List<UsingHistoryData>) :
    RecyclerView.Adapter<UsingHistoryViewHolder>() {

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsingHistoryViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_using_history, parent, false)
        return UsingHistoryViewHolder(inflatedView);
    }

    override fun onBindViewHolder(holder: UsingHistoryViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            bind(item)
        }
    }
}
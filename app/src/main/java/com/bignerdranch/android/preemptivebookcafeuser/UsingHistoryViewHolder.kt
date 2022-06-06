package com.bignerdranch.android.preemptivebookcafeuser

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_report_history.view.*
import kotlinx.android.synthetic.main.item_using_history.view.*

class UsingHistoryViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var view : View = v

    fun bind(item: UsingHistoryData) {
        view.historyDay.text = item.name
        view.historyDate.text = item.tel
        view.historyDeskNum.text = item.num
    }
}
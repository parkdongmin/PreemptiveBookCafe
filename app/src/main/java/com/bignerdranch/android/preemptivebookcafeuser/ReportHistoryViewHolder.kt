package com.bignerdranch.android.preemptivebookcafeuser

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_report_history.view.*

class ReportHistoryViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var view : View = v

    fun reportbind(item: ReportHistoryData) {
        view.reportDay.text = item.date
        view.reportDate.text = item.time
        view.reportDeskNum.text = item.num
    }
}
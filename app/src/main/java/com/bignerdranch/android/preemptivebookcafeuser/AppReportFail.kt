package com.bignerdranch.android.preemptivebookcafeuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_app_report_fail.*
import kotlinx.android.synthetic.main.activity_app_report_fail.idText
import kotlinx.android.synthetic.main.activity_app_report_fail.textView5
import kotlinx.android.synthetic.main.activity_app_report_fail.topBackSpace
import kotlinx.android.synthetic.main.app_desk_report_history.*

class AppReportFail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_report_fail)

        val id = MyApplication.prefs.getString("idStr", "")
        idText.setText(id.toString())

        textView5.setOnClickListener {
            var intent = Intent(this, AppReport::class.java) //다음 화면 이동을 위한 intent 객체 생성
            intent.putExtra("id",id)
            startActivity(intent)
            finish()
        }

        topBackSpace.setOnClickListener {
            var intent = Intent(this, AppLogIn::class.java) //다음 화면 이동을 위한 intent 객체 생성
            startActivity(intent)
            finish()
        }
    }
}
package com.bignerdranch.android.preemptivebookcafeuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import com.bignerdranch.android.preemptivebookcafeuser.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.app_report.*

class AppReport : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_report)

        dialogBtn.setOnClickListener {
            // Dialog만들기
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.desk_state_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("좌석상태 확인표")
            mBuilder.show()
        }
        reportBtn.setOnClickListener {
            var intent = Intent(this, AppReportSuccess::class.java) //다음 화면 이동을 위한 intent 객체 생성
            startActivity(intent)
            finish()
        }
    }
}
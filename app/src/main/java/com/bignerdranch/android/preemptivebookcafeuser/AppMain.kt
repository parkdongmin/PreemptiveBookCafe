package com.bignerdranch.android.preemptivebookcafeuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.app_main.*

class AppMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_main)

        var intentData = intent
        var id = intentData.getStringExtra("id")
        idText.setText(id.toString())

        useRegGoBtn.setOnClickListener {
            var intent = Intent(this, AppDeskUsingHistory::class.java) //다음 화면 이동을 위한 intent 객체 생성
            startActivity(intent)
            finish()
        }

        reportGoBtn.setOnClickListener {
            var intent = Intent(this, AppReport::class.java) //다음 화면 이동을 위한 intent 객체 생성
            startActivity(intent)
            finish()
        }
    }
}
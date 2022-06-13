package com.bignerdranch.android.preemptivebookcafeuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_app_report_fail.*

class AppLoginFail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_login_fail)

        textView5.setOnClickListener {
            var intent = Intent(this, AppLogIn::class.java) //다음 화면 이동을 위한 intent 객체 생성
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
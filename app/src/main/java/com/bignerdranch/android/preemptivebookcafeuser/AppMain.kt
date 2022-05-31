package com.bignerdranch.android.preemptivebookcafeuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.android.synthetic.main.app_main.*

class AppMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_main)

        var intentData = intent
        var id = intentData.getStringExtra("id")
        idText.setText(id.toString() + " ▼")

        val data:Array<String> = resources.getStringArray(R.array.logout)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                println(id)
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(data.get(p2) == "로그아웃"){
                    MyApplication.prefs.setString("RefreshToken", "")
                    MyApplication.prefs.setString("AccessToken", "")
                    MyApplication.prefs.setString("idStr", "")
                    AppLogInLink()
                }
            }
        }

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

    fun AppLogInLink(){
        var intent = Intent(this, AppLogIn::class.java) //다음 화면 이동을 위한 intent 객체 생성
        startActivity(intent)
        finish()
    }
}


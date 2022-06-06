package com.bignerdranch.android.preemptivebookcafeuser

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.app_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import java.time.LocalDateTime

class AppMain : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_main)

        var intentData = intent
        var id = intentData.getStringExtra("id")
        idText.setText(id.toString() + " ▼")

        var gson = GsonBuilder().setLenient().create()

        val retrofits = Retrofit.Builder()
            .baseUrl("http://3.36.156.88:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val services = retrofits.create(PostToken::class.java)

        val fbToken = MyApplication.prefs.getString("fbToken", "")
        Log.d("토큰 출력", "{$fbToken}")

        services.token(fbToken).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val result = response.body()
                if(response.code()==400){
                    //val jsonObject = JSONObject(response.errorBody().toString());
                    Log.d("에러 ", "${response.errorBody()?.string()!!}")
                }
                else{
                    Log.d("토큰" , "${response.raw()}")
                    Log.d("토큰" , "${result}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("토큰오류", "${t.localizedMessage}")
            }
        })

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
            intent.putExtra("id",id)
            startActivity(intent)
            finish()
        }

        reportGoBtn.setOnClickListener {
            var intent = Intent(this, AppReport::class.java) //다음 화면 이동을 위한 intent 객체 생성
            intent.putExtra("id",id)
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

interface PostToken{
    @Headers("accept: application/json", "content-type: application/json")
    @POST("/push/send/token")
    fun token(
        @Header("Authorization") token : String?
    ) : Call<String>
}
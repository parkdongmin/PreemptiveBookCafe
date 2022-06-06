package com.bignerdranch.android.preemptivebookcafeuser

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.app_desk_using_history.*
import kotlinx.android.synthetic.main.app_desk_using_history.idText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.time.LocalDateTime


class AppDeskUsingHistory : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_desk_using_history)

        var UsingHistoryDataList: MutableList<UsingHistoryData> = mutableListOf<UsingHistoryData>()

        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer())
        val gson: Gson = gsonBuilder.setPrettyPrinting().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.36.156.88:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val service = retrofit.create(UsingHistory::class.java)

        Log.d("AccessToken", "${MyApplication.prefs.getString("AccessToken", "")}")
        val token = "Bearer "+ MyApplication.prefs.getString("AccessToken", "")

        service.usingHistory(token)?.enqueue( object : Callback<Array<LogResponse>> {
            override fun onResponse(call: Call<Array<LogResponse>>, response: Response<Array<LogResponse>>) {
                if(response.code()==400){
                    Log.d("에러 ", "${response.errorBody()?.string()!!}")
                }
                else{

                    Log.d("로그현황" , "${response.raw()}")
                    Log.d("로그현황" , "${response.body()}")
                    var data : Array<LogResponse>? = response?.body()
                    for ( i in data!!){
                        //Log.d("data" , data[0].toString())
                        Log.d("data" , i.toString())
                    }
                    for(i in data.indices){
                        var usingHdate = data?.get(i)?.seatId.toString()
                        var usingHday = data?.get(i)?.logTime.toString()
                        var usingHnum = data?.get(i)?.id.toString()
                        var dataOb = UsingHistoryData(usingHdate, usingHday, usingHnum)
                        UsingHistoryDataList.add(i,dataOb)
                    }
                    //data?.get(0)?.
                    val adapter = UsingHistoryListAdapter(UsingHistoryDataList)
                    mRecyclerView.adapter = adapter
                    }
                }
            override fun onFailure(call: Call<Array<LogResponse>>, t: Throwable) {
                Log.e("로그현황", "${t.localizedMessage}")
            }
        })

        var intentData = intent
        var id = intentData.getStringExtra("id")
        idText.setText(id.toString())

        topBackSpace.setOnClickListener {
            var intent = Intent(this, AppLogIn::class.java) //다음 화면 이동을 위한 intent 객체 생성
            startActivity(intent)
            finish()
        }

        reportViewBtn.setOnClickListener {
            var intent = Intent(this, AppDeskReportHistory::class.java) //다음 화면 이동을 위한 intent 객체 생성
            intent.putExtra("id",id)
            startActivity(intent)
            finish()
        }
    }
}

interface UsingHistory{
    @GET("/log/register")
    fun usingHistory(
        @Header("Authorization") token : String?
    ) :  Call<Array<LogResponse>>
}



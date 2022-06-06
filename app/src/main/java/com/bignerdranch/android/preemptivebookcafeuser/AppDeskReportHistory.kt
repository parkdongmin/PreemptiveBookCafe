package com.bignerdranch.android.preemptivebookcafeuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.app_desk_report_history.*
import kotlinx.android.synthetic.main.app_desk_report_history.idText
import kotlinx.android.synthetic.main.app_desk_report_history.topBackSpace
import kotlinx.android.synthetic.main.app_desk_report_history.useViewBtn
import kotlinx.android.synthetic.main.app_desk_using_history.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import java.time.LocalDateTime

class AppDeskReportHistory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_desk_report_history)

        var ReportHistoryDataList: MutableList<ReportHistoryData> = mutableListOf<ReportHistoryData>()

        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer())
        val gson: Gson = gsonBuilder.setPrettyPrinting().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.36.156.88:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val service = retrofit.create(ReportHistory::class.java)

        Log.d("AccessToken", "${MyApplication.prefs.getString("AccessToken", "")}")
        val token = "Bearer "+ MyApplication.prefs.getString("AccessToken", "")

        service.reportHistory(token)?.enqueue( object : Callback<Array<LogResponse>> {
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
                        var reportDate = data?.get(i)?.seatId.toString()
                        var reportDay = data?.get(i)?.logTime.toString()
                        var reportNum = data?.get(i)?.id.toString()
                        var dataOb = ReportHistoryData(reportDate, reportDay, reportNum)
                        ReportHistoryDataList.add(i,dataOb)
                    }
                    //data?.get(0)?.
                    val adapter = ReportHistoryListAdapter(ReportHistoryDataList)
                    mrRecyclerView.adapter = adapter
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

        useViewBtn.setOnClickListener {
            var intent = Intent(this, AppDeskUsingHistory::class.java) //다음 화면 이동을 위한 intent 객체 생성
            intent.putExtra("id",id)
            startActivity(intent)
            finish()
        }
    }
}

interface ReportHistory{
    @GET("/log/report")
    fun reportHistory(
        @Header("Authorization") token : String?
    ) : Call<Array<LogResponse>>
}
package com.bignerdranch.android.preemptivebookcafeuser

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.app_report.*
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.time.LocalDateTime


class AppReport : AppCompatActivity(){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_report)

        var id = 0
        var cnt = 0
        var choiceDeskArray = Array<Int>(66) { 1 }

        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer())
        val gson: Gson = gsonBuilder.setPrettyPrinting().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.36.156.88:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val service = retrofit.create(DeskState::class.java)

        Log.d("AccessToken", "${MyApplication.prefs.getString("AccessToken", "")}")
        val token = "Bearer "+ MyApplication.prefs.getString("AccessToken", "")

        service.desk()?.enqueue( object : Callback<Array<Seats>> {
            override fun onResponse(call: Call<Array<Seats>>, response: Response<Array<Seats>>) {
                if(response.code()==400){
                    Log.d("에러 ", "${response.errorBody()?.string()!!}")
                }
                else{
                    Log.d("좌석현황" , "${response.raw()}")
                    var data : Array<Seats>? = response?.body()
                    /*for ( i in data!!){
                        Log.d("data" , data[0].status)
                        //Log.d("data" , i.toString())
                    }*/

                    seat1.setOnClickListener {
                        if(choiceDeskArray[0] % 2 == 0){
                            when (data?.get(0)?.status) {
                                "EMPTY" -> seat1.setImageResource(R.drawable.ellipse22)
                                "USED" -> seat1.setImageResource(R.drawable.placec2)
                                "AWAY" -> seat1.setImageResource(R.drawable.placec5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[0] = choiceDeskArray[0] + 1
                            Log.d("cnt", "$cnt")
                        }else{
                            seat1.setImageResource(R.drawable.placec4)
                            id = 1
                            cnt += 1
                            choiceDeskArray[0] = choiceDeskArray[0] + 1
                            Log.d("cnt", "$cnt")
                        }
                    }

                    seat2.setOnClickListener {
                        if(choiceDeskArray[1] % 2 == 0){
                            when (data?.get(1)?.status) {
                                "EMPTY" -> seat2.setImageResource(R.drawable.ellipse22)
                                "USED" -> seat2.setImageResource(R.drawable.placec2)
                                "AWAY" -> seat2.setImageResource(R.drawable.placec5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[1] += 1
                        }else{
                            seat2.setImageResource(R.drawable.placec4)
                            id = 2
                            cnt += 1
                            choiceDeskArray[1] += 1
                        }
                    }

                    seat3.setOnClickListener {
                        if(choiceDeskArray[2] % 2 == 0){
                            when (data?.get(2)?.status) {
                                "EMPTY" -> seat3.setImageResource(R.drawable.ellipse22)
                                "USED" -> seat3.setImageResource(R.drawable.placec2)
                                "AWAY" -> seat3.setImageResource(R.drawable.placec5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[2] += 1
                        }else{
                            seat3.setImageResource(R.drawable.placec4)
                            id = 3
                            cnt += 1
                            choiceDeskArray[2] += 1
                        }
                    }

                    seat4.setOnClickListener {
                        if(choiceDeskArray[3] % 2 == 0){
                            when (data?.get(3)?.status) {
                                "EMPTY" -> seat4.setImageResource(R.drawable.rectangle751)
                                "USED" -> seat4.setImageResource(R.drawable.placev2)
                                "AWAY" -> seat4.setImageResource(R.drawable.placev5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[3] += 1
                        }else{
                            seat4.setImageResource(R.drawable.placev4)
                            id = 4
                            cnt += 1
                            choiceDeskArray[3] += 1
                        }
                    }

                    seat5.setOnClickListener {
                        if(choiceDeskArray[4] % 2 == 0){
                            when (data?.get(4)?.status) {
                                "EMPTY" -> seat5.setImageResource(R.drawable.rectangle751)
                                "USED" -> seat5.setImageResource(R.drawable.placev2)
                                "AWAY" -> seat5.setImageResource(R.drawable.placev5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[4] += 1
                        }else{
                            seat5.setImageResource(R.drawable.placev4)
                            id = 5
                            cnt += 1
                            choiceDeskArray[4] += 1
                        }
                    }

                    seat6.setOnClickListener {
                        if(choiceDeskArray[5] % 2 == 0){
                            when (data?.get(5)?.status) {
                                "EMPTY" -> seat6.setImageResource(R.drawable.rectangle751)
                                "USED" -> seat6.setImageResource(R.drawable.placev2)
                                "AWAY" -> seat6.setImageResource(R.drawable.placev5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[5] += 1
                        }else{
                            seat6.setImageResource(R.drawable.placev4)
                            id = 6
                            cnt += 1
                            choiceDeskArray[5] += 1
                        }
                    }

                    seat7.setOnClickListener {
                        if(choiceDeskArray[6] % 2 == 0){
                            when (data?.get(6)?.status) {
                                "EMPTY" -> seat7.setImageResource(R.drawable.rectangle751)
                                "USED" -> seat7.setImageResource(R.drawable.placev2)
                                "AWAY" -> seat7.setImageResource(R.drawable.placev5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[6] += 1
                        }else{
                            seat7.setImageResource(R.drawable.placev4)
                            id = 7
                            cnt += 1
                            choiceDeskArray[6] += 1
                        }
                    }

                    seat8.setOnClickListener {
                        if(choiceDeskArray[7] % 2 == 0){
                            when (data?.get(7)?.status) {
                                "EMPTY" -> seat8.setImageResource(R.drawable.rectangle157)
                                "USED" -> seat8.setImageResource(R.drawable.placeh2)
                                "AWAY" -> seat8.setImageResource(R.drawable.placeh5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[7] += 1
                        }else{
                            seat8.setImageResource(R.drawable.placeh4)
                            id = 8
                            cnt += 1
                            choiceDeskArray[7] += 1
                        }
                    }

                    seat9.setOnClickListener {
                        if(choiceDeskArray[8] % 2 == 0){
                            when (data?.get(8)?.status) {
                                "EMPTY" -> seat9.setImageResource(R.drawable.rectangle157)
                                "USED" -> seat9.setImageResource(R.drawable.placeh2)
                                "AWAY" -> seat9.setImageResource(R.drawable.placeh5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[8] += 1
                        }else{
                            seat9.setImageResource(R.drawable.placeh4)
                            id = 9
                            cnt += 1
                            choiceDeskArray[8] += 1
                        }
                    }

                    seat10.setOnClickListener {
                        if(choiceDeskArray[9] % 2 == 0){
                            when (data?.get(9)?.status) {
                                "EMPTY" -> seat10.setImageResource(R.drawable.rectangle157)
                                "USED" -> seat10.setImageResource(R.drawable.placeh2)
                                "AWAY" -> seat10.setImageResource(R.drawable.placeh5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[9] += 1
                        }else{
                            seat10.setImageResource(R.drawable.placeh4)
                            id = 10
                            cnt += 1
                            choiceDeskArray[9] += 1
                        }
                    }

                    seat11.setOnClickListener {
                        if(choiceDeskArray[10] % 2 == 0){
                            when (data?.get(10)?.status) {
                                "EMPTY" -> seat11.setImageResource(R.drawable.rectangle157)
                                "USED" -> seat11.setImageResource(R.drawable.placeh2)
                                "AWAY" -> seat11.setImageResource(R.drawable.placeh5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[10] += 1
                        }else{
                            seat11.setImageResource(R.drawable.placeh4)
                            id = 11
                            cnt += 1
                            choiceDeskArray[10] += 1
                        }
                    }

                    seat12.setOnClickListener {
                        if(choiceDeskArray[11] % 2 == 0){
                            when (data?.get(11)?.status) {
                                "EMPTY" -> seat12.setImageResource(R.drawable.ellipse22)
                                "USED" -> seat12.setImageResource(R.drawable.placec2)
                                "AWAY" -> seat12.setImageResource(R.drawable.placec5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[11] += 1
                        }else{
                            seat12.setImageResource(R.drawable.placec4)
                            id = 12
                            cnt += 1
                            choiceDeskArray[11] += 1
                        }
                    }

                    seat13.setOnClickListener {
                        if(choiceDeskArray[12] % 2 == 0){
                            when (data?.get(12)?.status) {
                                "EMPTY" -> seat13.setImageResource(R.drawable.place1)
                                "USED" -> seat13.setImageResource(R.drawable.place2)
                                "AWAY" -> seat13.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[12] += 1
                        }else{
                            seat13.setImageResource(R.drawable.place4)
                            id = 13
                            cnt += 1
                            choiceDeskArray[12] += 1
                        }
                    }

                    seat14.setOnClickListener {
                        if(choiceDeskArray[13] % 2 == 0){
                            when (data?.get(13)?.status) {
                                "EMPTY" -> seat14.setImageResource(R.drawable.place1)
                                "USED" -> seat14.setImageResource(R.drawable.place2)
                                "AWAY" -> seat14.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[13] += 1
                        }else{
                            seat14.setImageResource(R.drawable.place4)
                            id = 14
                            cnt += 1
                            choiceDeskArray[13] += 1
                        }
                    }

                    seat15.setOnClickListener {
                        if(choiceDeskArray[14] % 2 == 0){
                            when (data?.get(14)?.status) {
                                "EMPTY" -> seat15.setImageResource(R.drawable.place1)
                                "USED" -> seat15.setImageResource(R.drawable.place2)
                                "AWAY" -> seat15.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[14] += 1
                        }else{
                            seat15.setImageResource(R.drawable.place4)
                            id = 15
                            cnt += 1
                            choiceDeskArray[14] += 1
                        }
                    }

                    seat16.setOnClickListener {
                        if(choiceDeskArray[15] % 2 == 0){
                            when (data?.get(15)?.status) {
                                "EMPTY" -> seat16.setImageResource(R.drawable.place1)
                                "USED" -> seat16.setImageResource(R.drawable.place2)
                                "AWAY" -> seat16.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[15] += 1
                        }else{
                            seat16.setImageResource(R.drawable.place4)
                            id = 16
                            cnt += 1
                            choiceDeskArray[15] += 1
                        }
                    }

                    seat17.setOnClickListener {
                        if(choiceDeskArray[16] % 2 == 0){
                            when (data?.get(16)?.status) {
                                "EMPTY" -> seat17.setImageResource(R.drawable.place1)
                                "USED" -> seat17.setImageResource(R.drawable.place2)
                                "AWAY" -> seat17.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[16] += 1
                        }else{
                            seat17.setImageResource(R.drawable.place4)
                            id = 17
                            cnt += 1
                            choiceDeskArray[16] += 1
                        }
                    }

                    seat18.setOnClickListener {
                        if(choiceDeskArray[17] % 2 == 0){
                            when (data?.get(17)?.status) {
                                "EMPTY" -> seat18.setImageResource(R.drawable.place1)
                                "USED" -> seat18.setImageResource(R.drawable.place2)
                                "AWAY" -> seat18.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[17] += 1
                        }else{
                            seat18.setImageResource(R.drawable.place4)
                            id = 18
                            cnt += 1
                            choiceDeskArray[17] += 1
                        }
                    }

                    seat19.setOnClickListener {
                        if(choiceDeskArray[18] % 2 == 0){
                            when (data?.get(18)?.status) {
                                "EMPTY" -> seat19.setImageResource(R.drawable.place1)
                                "USED" -> seat19.setImageResource(R.drawable.place2)
                                "AWAY" -> seat19.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[18] += 1
                        }else{
                            seat19.setImageResource(R.drawable.place4)
                            id = 19
                            cnt += 1
                            choiceDeskArray[18] += 1
                        }
                    }

                    seat20.setOnClickListener {
                        if(choiceDeskArray[19] % 2 == 0){
                            when (data?.get(19)?.status) {
                                "EMPTY" -> seat20.setImageResource(R.drawable.place1)
                                "USED" -> seat20.setImageResource(R.drawable.place2)
                                "AWAY" -> seat20.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[19] += 1
                        }else{
                            seat20.setImageResource(R.drawable.place4)
                            id = 20
                            cnt += 1
                            choiceDeskArray[19] += 1
                        }
                    }

                    seat21.setOnClickListener {
                        if(choiceDeskArray[20] % 2 == 0){
                            when (data?.get(20)?.status) {
                                "EMPTY" -> seat21.setImageResource(R.drawable.place1)
                                "USED" -> seat21.setImageResource(R.drawable.place2)
                                "AWAY" -> seat21.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[20] += 1
                        }else{
                            seat21.setImageResource(R.drawable.place4)
                            id = 21
                            cnt += 1
                            choiceDeskArray[20] += 1
                        }
                    }

                    seat22.setOnClickListener {
                        if(choiceDeskArray[21] % 2 == 0){
                            when (data?.get(21)?.status) {
                                "EMPTY" -> seat22.setImageResource(R.drawable.place1)
                                "USED" -> seat22.setImageResource(R.drawable.place2)
                                "AWAY" -> seat22.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[21] += 1
                        }else{
                            seat22.setImageResource(R.drawable.place4)
                            id = 22
                            cnt += 1
                            choiceDeskArray[21] += 1
                        }
                    }

                    seat23.setOnClickListener {
                        if(choiceDeskArray[22] % 2 == 0){
                            when (data?.get(22)?.status) {
                                "EMPTY" -> seat23.setImageResource(R.drawable.place1)
                                "USED" -> seat23.setImageResource(R.drawable.place2)
                                "AWAY" -> seat23.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[22] += 1
                        }else{
                            seat23.setImageResource(R.drawable.place4)
                            id = 23
                            cnt += 1
                            choiceDeskArray[22] += 1
                        }
                    }

                    seat24.setOnClickListener {
                        if(choiceDeskArray[23] % 2 == 0){
                            when (data?.get(23)?.status) {
                                "EMPTY" -> seat24.setImageResource(R.drawable.place1)
                                "USED" -> seat24.setImageResource(R.drawable.place2)
                                "AWAY" -> seat24.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[23] += 1
                        }else{
                            seat24.setImageResource(R.drawable.place4)
                            id = 24
                            cnt += 1
                            choiceDeskArray[23] += 1
                        }
                    }

                    seat25.setOnClickListener {
                        if(choiceDeskArray[24] % 2 == 0){
                            when (data?.get(24)?.status) {
                                "EMPTY" -> seat25.setImageResource(R.drawable.place1)
                                "USED" -> seat25.setImageResource(R.drawable.place2)
                                "AWAY" -> seat25.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[24] += 1
                        }else{
                            seat25.setImageResource(R.drawable.place4)
                            id = 25
                            cnt += 1
                            choiceDeskArray[24] += 1
                        }
                    }

                    seat26.setOnClickListener {
                        if(choiceDeskArray[25] % 2 == 0){
                            when (data?.get(25)?.status) {
                                "EMPTY" -> seat26.setImageResource(R.drawable.ellipse22)
                                "USED" -> seat26.setImageResource(R.drawable.placec2)
                                "AWAY" -> seat26.setImageResource(R.drawable.placec5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[25] += 1
                        }else{
                            seat26.setImageResource(R.drawable.placec4)
                            id = 26
                            cnt += 1
                            choiceDeskArray[25] += 1
                        }
                    }

                    seat27.setOnClickListener {
                        if(choiceDeskArray[26] % 2 == 0){
                            when (data?.get(26)?.status) {
                                "EMPTY" -> seat27.setImageResource(R.drawable.rectangle157)
                                "USED" -> seat27.setImageResource(R.drawable.placeh2)
                                "AWAY" -> seat27.setImageResource(R.drawable.placeh5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[26] += 1
                        }else{
                            seat27.setImageResource(R.drawable.placeh4)
                            id = 27
                            cnt += 1
                            choiceDeskArray[26] += 1
                        }
                    }

                    seat28.setOnClickListener {
                        if(choiceDeskArray[27] % 2 == 0){
                            when (data?.get(27)?.status) {
                                "EMPTY" -> seat28.setImageResource(R.drawable.rectangle157)
                                "USED" -> seat28.setImageResource(R.drawable.placeh2)
                                "AWAY" -> seat28.setImageResource(R.drawable.placeh5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[27] += 1
                        }else{
                            seat28.setImageResource(R.drawable.placeh4)
                            id = 28
                            cnt += 1
                            choiceDeskArray[27] += 1
                        }
                    }

                    seat29.setOnClickListener {
                        if(choiceDeskArray[28] % 2 == 0){
                            when (data?.get(28)?.status) {
                                "EMPTY" -> seat29.setImageResource(R.drawable.rectangle157)
                                "USED" -> seat29.setImageResource(R.drawable.placeh2)
                                "AWAY" -> seat29.setImageResource(R.drawable.placeh5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[28] += 1
                        }else{
                            seat29.setImageResource(R.drawable.placeh4)
                            id = 29
                            cnt += 1
                            choiceDeskArray[28] += 1
                        }
                    }

                    seat30.setOnClickListener {
                        if(choiceDeskArray[29] % 2 == 0){
                            when (data?.get(29)?.status) {
                                "EMPTY" -> seat30.setImageResource(R.drawable.rectangle157)
                                "USED" -> seat30.setImageResource(R.drawable.placeh2)
                                "AWAY" -> seat30.setImageResource(R.drawable.placeh5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[29] += 1
                        }else{
                            seat30.setImageResource(R.drawable.placeh4)
                            id = 30
                            cnt += 1
                            choiceDeskArray[29] += 1
                        }
                    }

                    seat31.setOnClickListener {
                        if(choiceDeskArray[30] % 2 == 0){
                            when (data?.get(30)?.status) {
                                "EMPTY" -> seat31.setImageResource(R.drawable.place1)
                                "USED" -> seat31.setImageResource(R.drawable.place2)
                                "AWAY" -> seat31.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[30] += 1
                        }else{
                            seat31.setImageResource(R.drawable.place4)
                            id = 31
                            cnt += 1
                            choiceDeskArray[30] += 1
                        }
                    }

                    seat32.setOnClickListener {
                        if(choiceDeskArray[31] % 2 == 0){
                            when (data?.get(31)?.status) {
                                "EMPTY" -> seat32.setImageResource(R.drawable.place1)
                                "USED" -> seat32.setImageResource(R.drawable.place2)
                                "AWAY" -> seat32.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[31] += 1
                        }else{
                            seat32.setImageResource(R.drawable.place4)
                            id = 32
                            cnt += 1
                            choiceDeskArray[31] += 1
                        }
                    }

                    seat33.setOnClickListener {
                        if(choiceDeskArray[32] % 2 == 0){
                            when (data?.get(32)?.status) {
                                "EMPTY" -> seat33.setImageResource(R.drawable.place1)
                                "USED" -> seat33.setImageResource(R.drawable.place2)
                                "AWAY" -> seat33.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[32] += 1
                        }else{
                            seat33.setImageResource(R.drawable.place4)
                            id = 33
                            cnt += 1
                            choiceDeskArray[32] += 1
                        }
                    }

                    seat34.setOnClickListener {
                        if(choiceDeskArray[33] % 2 == 0){
                            when (data?.get(33)?.status) {
                                "EMPTY" -> seat34.setImageResource(R.drawable.place1)
                                "USED" -> seat34.setImageResource(R.drawable.place2)
                                "AWAY" -> seat34.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[33] += 1
                        }else{
                            seat34.setImageResource(R.drawable.place4)
                            id = 34
                            cnt += 1
                            choiceDeskArray[33] += 1
                        }
                    }

                    seat35.setOnClickListener {
                        if(choiceDeskArray[34] % 2 == 0){
                            when (data?.get(34)?.status) {
                                "EMPTY" -> seat35.setImageResource(R.drawable.place1)
                                "USED" -> seat35.setImageResource(R.drawable.place2)
                                "AWAY" -> seat35.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[34] += 1
                        }else{
                            seat35.setImageResource(R.drawable.place4)
                            id = 35
                            cnt += 1
                            choiceDeskArray[34] += 1
                        }
                    }

                    seat36.setOnClickListener {
                        if(choiceDeskArray[35] % 2 == 0){
                            when (data?.get(35)?.status) {
                                "EMPTY" -> seat36.setImageResource(R.drawable.place1)
                                "USED" -> seat36.setImageResource(R.drawable.place2)
                                "AWAY" -> seat36.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[35] += 1
                        }else{
                            seat36.setImageResource(R.drawable.place4)
                            id = 36
                            cnt += 1
                            choiceDeskArray[35] += 1
                        }
                    }

                    seat37.setOnClickListener {
                        if(choiceDeskArray[36] % 2 == 0){
                            when (data?.get(36)?.status) {
                                "EMPTY" -> seat37.setImageResource(R.drawable.place1)
                                "USED" -> seat37.setImageResource(R.drawable.place2)
                                "AWAY" -> seat37.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[36] += 1
                        }else{
                            seat37.setImageResource(R.drawable.place4)
                            id = 37
                            cnt += 1
                            choiceDeskArray[36] += 1
                        }
                    }

                    seat38.setOnClickListener {
                        if(choiceDeskArray[37] % 2 == 0){
                            when (data?.get(37)?.status) {
                                "EMPTY" -> seat38.setImageResource(R.drawable.place1)
                                "USED" -> seat38.setImageResource(R.drawable.place2)
                                "AWAY" -> seat38.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[37] += 1
                        }else{
                            seat38.setImageResource(R.drawable.place4)
                            id = 38
                            cnt += 1
                            choiceDeskArray[37] += 1
                        }
                    }

                    seat39.setOnClickListener {
                        if(choiceDeskArray[38] % 2 == 0){
                            when (data?.get(38)?.status) {
                                "EMPTY" -> seat39.setImageResource(R.drawable.place1)
                                "USED" -> seat39.setImageResource(R.drawable.place2)
                                "AWAY" -> seat39.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[38] += 1
                        }else{
                            seat39.setImageResource(R.drawable.place4)
                            id = 39
                            cnt += 1
                            choiceDeskArray[38] += 1
                        }
                    }

                    seat40.setOnClickListener {
                        if(choiceDeskArray[39] % 2 == 0){
                            when (data?.get(39)?.status) {
                                "EMPTY" -> seat40.setImageResource(R.drawable.place1)
                                "USED" -> seat40.setImageResource(R.drawable.place2)
                                "AWAY" -> seat40.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[39] += 1
                        }else{
                            seat40.setImageResource(R.drawable.place4)
                            id = 40
                            cnt += 1
                            choiceDeskArray[39] += 1
                        }
                    }

                    seat41.setOnClickListener {
                        if(choiceDeskArray[40] % 2 == 0){
                            when (data?.get(40)?.status) {
                                "EMPTY" -> seat41.setImageResource(R.drawable.place1)
                                "USED" -> seat41.setImageResource(R.drawable.place2)
                                "AWAY" -> seat41.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[40] += 1
                        }else{
                            seat41.setImageResource(R.drawable.place4)
                            id = 41
                            cnt += 1
                            choiceDeskArray[40] += 1
                        }
                    }

                    seat42.setOnClickListener {
                        if(choiceDeskArray[41] % 2 == 0){
                            when (data?.get(41)?.status) {
                                "EMPTY" -> seat42.setImageResource(R.drawable.place1)
                                "USED" -> seat42.setImageResource(R.drawable.place2)
                                "AWAY" -> seat42.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[41] += 1
                        }else{
                            seat42.setImageResource(R.drawable.place4)
                            id = 42
                            cnt += 1
                            choiceDeskArray[41] += 1
                        }
                    }

                    seat43.setOnClickListener {
                        if(choiceDeskArray[42] % 2 == 0){
                            when (data?.get(42)?.status) {
                                "EMPTY" -> seat43.setImageResource(R.drawable.place1)
                                "USED" -> seat43.setImageResource(R.drawable.place2)
                                "AWAY" -> seat43.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[42] += 1
                        }else{
                            seat43.setImageResource(R.drawable.place4)
                            id = 43
                            cnt += 1
                            choiceDeskArray[42] += 1
                        }
                    }

                    seat44.setOnClickListener {
                        if(choiceDeskArray[43] % 2 == 0){
                            when (data?.get(43)?.status) {
                                "EMPTY" -> seat44.setImageResource(R.drawable.place1)
                                "USED" -> seat44.setImageResource(R.drawable.place2)
                                "AWAY" -> seat44.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[43] += 1
                        }else{
                            seat44.setImageResource(R.drawable.place4)
                            id = 44
                            cnt += 1
                            choiceDeskArray[43] += 1
                        }
                    }

                    seat45.setOnClickListener {
                        if(choiceDeskArray[44] % 2 == 0){
                            when (data?.get(44)?.status) {
                                "EMPTY" -> seat45.setImageResource(R.drawable.place1)
                                "USED" -> seat45.setImageResource(R.drawable.place2)
                                "AWAY" -> seat45.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[44] += 1
                        }else{
                            seat45.setImageResource(R.drawable.place4)
                            id = 45
                            cnt += 1
                            choiceDeskArray[44] += 1
                        }
                    }

                    seat46.setOnClickListener {
                        if(choiceDeskArray[45] % 2 == 0){
                            when (data?.get(45)?.status) {
                                "EMPTY" -> seat46.setImageResource(R.drawable.place1)
                                "USED" -> seat46.setImageResource(R.drawable.place2)
                                "AWAY" -> seat46.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[45] += 1
                        }else{
                            seat46.setImageResource(R.drawable.place4)
                            id = 46
                            cnt += 1
                            choiceDeskArray[45] += 1
                        }
                    }

                    seat47.setOnClickListener {
                        if(choiceDeskArray[46] % 2 == 0){
                            when (data?.get(46)?.status) {
                                "EMPTY" -> seat47.setImageResource(R.drawable.place1)
                                "USED" -> seat47.setImageResource(R.drawable.place2)
                                "AWAY" -> seat47.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[46] += 1
                        }else{
                            seat47.setImageResource(R.drawable.place4)
                            id = 47
                            cnt += 1
                            choiceDeskArray[46] += 1
                        }
                    }

                    seat48.setOnClickListener {
                        if(choiceDeskArray[47] % 2 == 0){
                            when (data?.get(47)?.status) {
                                "EMPTY" -> seat48.setImageResource(R.drawable.place1)
                                "USED" -> seat48.setImageResource(R.drawable.place2)
                                "AWAY" -> seat48.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[47] += 1
                        }else{
                            seat48.setImageResource(R.drawable.place4)
                            id = 48
                            cnt += 1
                            choiceDeskArray[47] += 1
                        }
                    }

                    seat49.setOnClickListener {
                        if(choiceDeskArray[48] % 2 == 0){
                            when (data?.get(48)?.status) {
                                "EMPTY" -> seat49.setImageResource(R.drawable.place1)
                                "USED" -> seat49.setImageResource(R.drawable.place2)
                                "AWAY" -> seat49.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[48] += 1
                        }else{
                            seat49.setImageResource(R.drawable.place4)
                            id = 49
                            cnt += 1
                            choiceDeskArray[48] += 1
                        }
                    }

                    seat50.setOnClickListener {
                        if(choiceDeskArray[49] % 2 == 0){
                            when (data?.get(49)?.status) {
                                "EMPTY" -> seat50.setImageResource(R.drawable.place1)
                                "USED" -> seat50.setImageResource(R.drawable.place2)
                                "AWAY" -> seat50.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[49] += 1
                        }else{
                            seat50.setImageResource(R.drawable.place4)
                            id = 50
                            cnt += 1
                            choiceDeskArray[49] += 1
                        }
                    }

                    seat51.setOnClickListener {
                        if(choiceDeskArray[50] % 2 == 0){
                            when (data?.get(50)?.status) {
                                "EMPTY" -> seat51.setImageResource(R.drawable.place1)
                                "USED" -> seat51.setImageResource(R.drawable.place2)
                                "AWAY" -> seat51.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[50] += 1
                        }else{
                            seat51.setImageResource(R.drawable.place4)
                            id = 51
                            cnt += 1
                            choiceDeskArray[50] += 1
                        }
                    }

                    seat52.setOnClickListener {
                        if(choiceDeskArray[51] % 2 == 0){
                            when (data?.get(51)?.status) {
                                "EMPTY" -> seat52.setImageResource(R.drawable.place1)
                                "USED" -> seat52.setImageResource(R.drawable.place2)
                                "AWAY" -> seat52.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[51] += 1
                        }else{
                            seat52.setImageResource(R.drawable.place4)
                            id = 52
                            cnt += 1
                            choiceDeskArray[51] += 1
                        }
                    }

                    seat53.setOnClickListener {
                        if(choiceDeskArray[52] % 2 == 0){
                            when (data?.get(52)?.status) {
                                "EMPTY" -> seat53.setImageResource(R.drawable.place1)
                                "USED" -> seat53.setImageResource(R.drawable.place2)
                                "AWAY" -> seat53.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[52] += 1
                        }else{
                            seat53.setImageResource(R.drawable.place4)
                            id = 53
                            cnt += 1
                            choiceDeskArray[52] += 1
                        }
                    }

                    seat54.setOnClickListener {
                        if(choiceDeskArray[53] % 2 == 0){
                            when (data?.get(53)?.status) {
                                "EMPTY" -> seat54.setImageResource(R.drawable.place1)
                                "USED" -> seat54.setImageResource(R.drawable.place2)
                                "AWAY" -> seat54.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[53] += 1
                        }else{
                            seat54.setImageResource(R.drawable.place4)
                            id = 54
                            cnt += 1
                            choiceDeskArray[53] += 1
                        }
                    }

                    seat55.setOnClickListener {
                        if(choiceDeskArray[54] % 2 == 0){
                            when (data?.get(54)?.status) {
                                "EMPTY" -> seat55.setImageResource(R.drawable.place1)
                                "USED" -> seat55.setImageResource(R.drawable.place2)
                                "AWAY" -> seat55.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[54] += 1
                        }else{
                            seat55.setImageResource(R.drawable.place4)
                            id = 55
                            cnt += 1
                            choiceDeskArray[54] += 1
                        }
                    }

                    seat56.setOnClickListener {
                        if(choiceDeskArray[55] % 2 == 0){
                            when (data?.get(55)?.status) {
                                "EMPTY" -> seat56.setImageResource(R.drawable.place1)
                                "USED" -> seat56.setImageResource(R.drawable.place2)
                                "AWAY" -> seat56.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[55] += 1
                        }else{
                            seat56.setImageResource(R.drawable.place4)
                            id = 56
                            cnt += 1
                            choiceDeskArray[55] += 1
                        }
                    }

                    seat57.setOnClickListener {
                        if(choiceDeskArray[56] % 2 == 0){
                            when (data?.get(56)?.status) {
                                "EMPTY" -> seat57.setImageResource(R.drawable.place1)
                                "USED" -> seat57.setImageResource(R.drawable.place2)
                                "AWAY" -> seat57.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[56] += 1
                        }else{
                            seat57.setImageResource(R.drawable.place4)
                            id = 57
                            cnt += 1
                            choiceDeskArray[56] += 1
                        }
                    }

                    seat58.setOnClickListener {
                        if(choiceDeskArray[57] % 2 == 0){
                            when (data?.get(57)?.status) {
                                "EMPTY" -> seat58.setImageResource(R.drawable.ellipse22)
                                "USED" -> seat58.setImageResource(R.drawable.placec2)
                                "AWAY" -> seat58.setImageResource(R.drawable.placec5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[57] += 1
                        }else{
                            seat58.setImageResource(R.drawable.placec4)
                            id = 58
                            cnt += 1
                            choiceDeskArray[57] += 1
                        }
                    }

                    seat59.setOnClickListener {
                        if(choiceDeskArray[58] % 2 == 0){
                            when (data?.get(58)?.status) {
                                "EMPTY" -> seat59.setImageResource(R.drawable.ellipse22)
                                "USED" -> seat59.setImageResource(R.drawable.placec2)
                                "AWAY" -> seat59.setImageResource(R.drawable.placec5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[58] += 1
                        }else{
                            seat59.setImageResource(R.drawable.placec4)
                            id = 59
                            cnt += 1
                            choiceDeskArray[58] += 1
                        }
                    }

                    seat60.setOnClickListener {
                        if(choiceDeskArray[59] % 2 == 0){
                            when (data?.get(59)?.status) {
                                "EMPTY" -> seat60.setImageResource(R.drawable.ellipse22)
                                "USED" -> seat60.setImageResource(R.drawable.placec2)
                                "AWAY" -> seat60.setImageResource(R.drawable.placec5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[59] += 1
                        }else{
                            seat60.setImageResource(R.drawable.placec4)
                            id = 60
                            cnt += 1
                            choiceDeskArray[59] += 1
                        }
                    }

                    seat61.setOnClickListener {
                        if(choiceDeskArray[60] % 2 == 0){
                            when (data?.get(60)?.status) {
                                "EMPTY" -> seat61.setImageResource(R.drawable.ellipse22)
                                "USED" -> seat61.setImageResource(R.drawable.placec2)
                                "AWAY" -> seat61.setImageResource(R.drawable.placec5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[60] += 1
                        }else{
                            seat61.setImageResource(R.drawable.placec4)
                            id = 61
                            cnt += 1
                            choiceDeskArray[60] += 1
                        }
                    }

                    seat62.setOnClickListener {
                        if(choiceDeskArray[61] % 2 == 0){
                            when (data?.get(61)?.status) {
                                "EMPTY" -> seat62.setImageResource(R.drawable.ellipse22)
                                "USED" -> seat62.setImageResource(R.drawable.placec2)
                                "AWAY" -> seat62.setImageResource(R.drawable.placec5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[61] += 1
                        }else{
                            seat62.setImageResource(R.drawable.placec4)
                            id = 62
                            cnt += 1
                            choiceDeskArray[61] += 1
                        }
                    }

                    seat63.setOnClickListener {
                        if(choiceDeskArray[62] % 2 == 0){
                            when (data?.get(62)?.status) {
                                "EMPTY" -> seat63.setImageResource(R.drawable.ellipse22)
                                "USED" -> seat63.setImageResource(R.drawable.placec2)
                                "AWAY" -> seat63.setImageResource(R.drawable.placec5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[62] += 1
                        }else{
                            seat63.setImageResource(R.drawable.placec4)
                            id = 63
                            cnt += 1
                            choiceDeskArray[62] += 1
                        }
                    }

                    seat64.setOnClickListener {
                        if(choiceDeskArray[63] % 2 == 0){
                            when (data?.get(63)?.status) {
                                "EMPTY" -> seat64.setImageResource(R.drawable.place1)
                                "USED" -> seat64.setImageResource(R.drawable.place2)
                                "AWAY" -> seat64.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[63] += 1
                        }else{
                            seat64.setImageResource(R.drawable.place4)
                            id = 64
                            cnt += 1
                            choiceDeskArray[63] += 1
                        }
                    }

                    seat65.setOnClickListener {
                        if(choiceDeskArray[64] % 2 == 0){
                            when (data?.get(64)?.status) {
                                "EMPTY" -> seat65.setImageResource(R.drawable.place1)
                                "USED" -> seat65.setImageResource(R.drawable.place2)
                                "AWAY" -> seat65.setImageResource(R.drawable.place5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[64] += 1
                        }else{
                            seat65.setImageResource(R.drawable.place4)
                            id = 65
                            cnt += 1
                            choiceDeskArray[64] += 1
                        }
                    }

                    seat66.setOnClickListener {
                        if(choiceDeskArray[65] % 2 == 0){
                            when (data?.get(65)?.status) {
                                "EMPTY" -> seat66.setImageResource(R.drawable.ellipse22)
                                "USED" -> seat66.setImageResource(R.drawable.placec2)
                                "AWAY" -> seat66.setImageResource(R.drawable.placec5)
                                else -> Log.e("error","error")
                            }
                            id = 0
                            cnt -= 1
                            choiceDeskArray[65] += 1
                        }else{
                            seat66.setImageResource(R.drawable.placec4)
                            id = 66
                            cnt += 1
                            choiceDeskArray[65] += 1
                        }
                    }




                    when (data?.get(0)?.status) {
                        "EMPTY" -> seat1.setImageResource(R.drawable.ellipse22)
                        "USED" -> seat1.setImageResource(R.drawable.placec2)
                        "AWAY" -> seat1.setImageResource(R.drawable.placec5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(1)?.status) {
                        "EMPTY" -> seat2.setImageResource(R.drawable.ellipse22)
                        "USED" -> seat2.setImageResource(R.drawable.placec2)
                        "AWAY" -> seat2.setImageResource(R.drawable.placec5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(2)?.status) {
                        "EMPTY" -> seat3.setImageResource(R.drawable.ellipse22)
                        "USED" -> seat3.setImageResource(R.drawable.placec2)
                        "AWAY" -> seat3.setImageResource(R.drawable.placec5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(3)?.status) {
                        "EMPTY" -> seat4.setImageResource(R.drawable.rectangle751)
                        "USED" -> seat4.setImageResource(R.drawable.placev2)
                        "AWAY" -> seat4.setImageResource(R.drawable.placev5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(4)?.status) {
                        "EMPTY" -> seat5.setImageResource(R.drawable.rectangle751)
                        "USED" -> seat5.setImageResource(R.drawable.placev2)
                        "AWAY" -> seat5.setImageResource(R.drawable.placev5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(5)?.status) {
                        "EMPTY" -> seat6.setImageResource(R.drawable.rectangle751)
                        "USED" -> seat6.setImageResource(R.drawable.placev2)
                        "AWAY" -> seat6.setImageResource(R.drawable.placev5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(6)?.status) {
                        "EMPTY" -> seat7.setImageResource(R.drawable.rectangle751)
                        "USED" -> seat7.setImageResource(R.drawable.placev2)
                        "AWAY" -> seat7.setImageResource(R.drawable.placev5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(7)?.status) {
                        "EMPTY" -> seat8.setImageResource(R.drawable.rectangle157)
                        "USED" -> seat8.setImageResource(R.drawable.placeh2)
                        "AWAY" -> seat8.setImageResource(R.drawable.placeh5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(8)?.status) {
                        "EMPTY" -> seat9.setImageResource(R.drawable.rectangle157)
                        "USED" -> seat9.setImageResource(R.drawable.placeh2)
                        "AWAY" -> seat9.setImageResource(R.drawable.placeh5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(9)?.status) {
                        "EMPTY" -> seat10.setImageResource(R.drawable.rectangle157)
                        "USED" -> seat10.setImageResource(R.drawable.placeh2)
                        "AWAY" -> seat10.setImageResource(R.drawable.placeh5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(10)?.status) {
                        "EMPTY" -> seat11.setImageResource(R.drawable.rectangle157)
                        "USED" -> seat11.setImageResource(R.drawable.placeh2)
                        "AWAY" -> seat11.setImageResource(R.drawable.placeh5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(11)?.status) {
                        "EMPTY" -> seat12.setImageResource(R.drawable.ellipse22)
                        "USED" -> seat12.setImageResource(R.drawable.placec2)
                        "AWAY" -> seat12.setImageResource(R.drawable.placec5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(12)?.status) {
                        "EMPTY" -> seat13.setImageResource(R.drawable.place1)
                        "USED" -> seat13.setImageResource(R.drawable.place2)
                        "AWAY" -> seat13.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(13)?.status) {
                        "EMPTY" -> seat14.setImageResource(R.drawable.place1)
                        "USED" -> seat14.setImageResource(R.drawable.place2)
                        "AWAY" -> seat14.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(14)?.status) {
                        "EMPTY" -> seat15.setImageResource(R.drawable.place1)
                        "USED" -> seat15.setImageResource(R.drawable.place2)
                        "AWAY" -> seat15.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(15)?.status) {
                        "EMPTY" -> seat16.setImageResource(R.drawable.place1)
                        "USED" -> seat16.setImageResource(R.drawable.place2)
                        "AWAY" -> seat16.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(17)?.status) {
                        "EMPTY" -> seat18.setImageResource(R.drawable.place1)
                        "USED" -> seat18.setImageResource(R.drawable.place2)
                        "AWAY" -> seat18.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(18)?.status) {
                        "EMPTY" -> seat19.setImageResource(R.drawable.place1)
                        "USED" -> seat19.setImageResource(R.drawable.place2)
                        "AWAY" -> seat19.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(19)?.status) {
                        "EMPTY" -> seat20.setImageResource(R.drawable.place1)
                        "USED" -> seat20.setImageResource(R.drawable.place2)
                        "AWAY" -> seat20.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(20)?.status) {
                        "EMPTY" -> seat21.setImageResource(R.drawable.place1)
                        "USED" -> seat21.setImageResource(R.drawable.place2)
                        "AWAY" -> seat21.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(21)?.status) {
                        "EMPTY" -> seat22.setImageResource(R.drawable.place1)
                        "USED" -> seat22.setImageResource(R.drawable.place2)
                        "AWAY" -> seat22.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(22)?.status) {
                        "EMPTY" -> seat23.setImageResource(R.drawable.place1)
                        "USED" -> seat23.setImageResource(R.drawable.place2)
                        "AWAY" -> seat23.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(23)?.status) {
                        "EMPTY" -> seat24.setImageResource(R.drawable.place1)
                        "USED" -> seat24.setImageResource(R.drawable.place2)
                        "AWAY" -> seat24.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(24)?.status) {
                        "EMPTY" -> seat25.setImageResource(R.drawable.place1)
                        "USED" -> seat25.setImageResource(R.drawable.place2)
                        "AWAY" -> seat25.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(25)?.status) {
                        "EMPTY" -> seat26.setImageResource(R.drawable.ellipse22)
                        "USED" -> seat26.setImageResource(R.drawable.placec2)
                        "AWAY" -> seat26.setImageResource(R.drawable.placec5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(26)?.status) {
                        "EMPTY" -> seat27.setImageResource(R.drawable.rectangle157)
                        "USED" -> seat27.setImageResource(R.drawable.placeh2)
                        "AWAY" -> seat27.setImageResource(R.drawable.placeh5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(27)?.status) {
                        "EMPTY" -> seat28.setImageResource(R.drawable.rectangle157)
                        "USED" -> seat28.setImageResource(R.drawable.placeh2)
                        "AWAY" -> seat28.setImageResource(R.drawable.placeh5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(28)?.status) {
                        "EMPTY" -> seat29.setImageResource(R.drawable.rectangle157)
                        "USED" -> seat29.setImageResource(R.drawable.placeh2)
                        "AWAY" -> seat29.setImageResource(R.drawable.placeh5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(29)?.status) {
                        "EMPTY" -> seat30.setImageResource(R.drawable.rectangle157)
                        "USED" -> seat30.setImageResource(R.drawable.placeh2)
                        "AWAY" -> seat30.setImageResource(R.drawable.placeh5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(30)?.status) {
                        "EMPTY" -> seat31.setImageResource(R.drawable.place1)
                        "USED" -> seat31.setImageResource(R.drawable.place2)
                        "AWAY" -> seat31.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(31)?.status) {
                        "EMPTY" -> seat32.setImageResource(R.drawable.place1)
                        "USED" -> seat32.setImageResource(R.drawable.place2)
                        "AWAY" -> seat32.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(32)?.status) {
                        "EMPTY" -> seat33.setImageResource(R.drawable.place1)
                        "USED" -> seat33.setImageResource(R.drawable.place2)
                        "AWAY" -> seat33.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(33)?.status) {
                        "EMPTY" -> seat34.setImageResource(R.drawable.place1)
                        "USED" -> seat34.setImageResource(R.drawable.place2)
                        "AWAY" -> seat34.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(34)?.status) {
                        "EMPTY" -> seat35.setImageResource(R.drawable.place1)
                        "USED" -> seat35.setImageResource(R.drawable.place2)
                        "AWAY" -> seat35.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(35)?.status) {
                        "EMPTY" -> seat36.setImageResource(R.drawable.place1)
                        "USED" -> seat36.setImageResource(R.drawable.place2)
                        "AWAY" -> seat36.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(36)?.status) {
                        "EMPTY" -> seat37.setImageResource(R.drawable.place1)
                        "USED" -> seat37.setImageResource(R.drawable.place2)
                        "AWAY" -> seat37.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(37)?.status) {
                        "EMPTY" -> seat38.setImageResource(R.drawable.place1)
                        "USED" -> seat38.setImageResource(R.drawable.place2)
                        "AWAY" -> seat38.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(38)?.status) {
                        "EMPTY" -> seat39.setImageResource(R.drawable.place1)
                        "USED" -> seat39.setImageResource(R.drawable.place2)
                        "AWAY" -> seat39.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(39)?.status) {
                        "EMPTY" -> seat40.setImageResource(R.drawable.place1)
                        "USED" -> seat40.setImageResource(R.drawable.place2)
                        "AWAY" -> seat40.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(40)?.status) {
                        "EMPTY" -> seat41.setImageResource(R.drawable.place1)
                        "USED" -> seat41.setImageResource(R.drawable.place2)
                        "AWAY" -> seat41.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(41)?.status) {
                        "EMPTY" -> seat42.setImageResource(R.drawable.place1)
                        "USED" -> seat42.setImageResource(R.drawable.place2)
                        "AWAY" -> seat42.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(42)?.status) {
                        "EMPTY" -> seat43.setImageResource(R.drawable.place1)
                        "USED" -> seat43.setImageResource(R.drawable.place2)
                        "AWAY" -> seat43.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(43)?.status) {
                        "EMPTY" -> seat44.setImageResource(R.drawable.place1)
                        "USED" -> seat44.setImageResource(R.drawable.place2)
                        "AWAY" -> seat44.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(44)?.status) {
                        "EMPTY" -> seat45.setImageResource(R.drawable.place1)
                        "USED" -> seat45.setImageResource(R.drawable.place2)
                        "AWAY" -> seat45.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(45)?.status) {
                        "EMPTY" -> seat46.setImageResource(R.drawable.place1)
                        "USED" -> seat46.setImageResource(R.drawable.place2)
                        "AWAY" -> seat46.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(46)?.status) {
                        "EMPTY" -> seat47.setImageResource(R.drawable.place1)
                        "USED" -> seat47.setImageResource(R.drawable.place2)
                        "AWAY" -> seat47.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(47)?.status) {
                        "EMPTY" -> seat48.setImageResource(R.drawable.place1)
                        "USED" -> seat48.setImageResource(R.drawable.place2)
                        "AWAY" -> seat48.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(48)?.status) {
                        "EMPTY" -> seat49.setImageResource(R.drawable.place1)
                        "USED" -> seat49.setImageResource(R.drawable.place2)
                        "AWAY" -> seat49.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(49)?.status) {
                        "EMPTY" -> seat50.setImageResource(R.drawable.place1)
                        "USED" -> seat50.setImageResource(R.drawable.place2)
                        "AWAY" -> seat50.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(50)?.status) {
                        "EMPTY" -> seat51.setImageResource(R.drawable.place1)
                        "USED" -> seat51.setImageResource(R.drawable.place2)
                        "AWAY" -> seat51.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(51)?.status) {
                        "EMPTY" -> seat52.setImageResource(R.drawable.place1)
                        "USED" -> seat52.setImageResource(R.drawable.place2)
                        "AWAY" -> seat52.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(52)?.status) {
                        "EMPTY" -> seat53.setImageResource(R.drawable.place1)
                        "USED" -> seat53.setImageResource(R.drawable.place2)
                        "AWAY" -> seat53.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(53)?.status) {
                        "EMPTY" -> seat54.setImageResource(R.drawable.place1)
                        "USED" -> seat54.setImageResource(R.drawable.place2)
                        "AWAY" -> seat54.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(54)?.status) {
                        "EMPTY" -> seat55.setImageResource(R.drawable.place1)
                        "USED" -> seat55.setImageResource(R.drawable.place2)
                        "AWAY" -> seat55.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(55)?.status) {
                        "EMPTY" -> seat56.setImageResource(R.drawable.place1)
                        "USED" -> seat56.setImageResource(R.drawable.place2)
                        "AWAY" -> seat56.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(56)?.status) {
                        "EMPTY" -> seat57.setImageResource(R.drawable.place1)
                        "USED" -> seat57.setImageResource(R.drawable.place2)
                        "AWAY" -> seat57.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(57)?.status) {
                        "EMPTY" -> seat58.setImageResource(R.drawable.ellipse22)
                        "USED" -> seat58.setImageResource(R.drawable.placec2)
                        "AWAY" -> seat58.setImageResource(R.drawable.placec5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(58)?.status) {
                        "EMPTY" -> seat59.setImageResource(R.drawable.ellipse22)
                        "USED" -> seat59.setImageResource(R.drawable.placec2)
                        "AWAY" -> seat59.setImageResource(R.drawable.placec5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(59)?.status) {
                        "EMPTY" -> seat60.setImageResource(R.drawable.ellipse22)
                        "USED" -> seat60.setImageResource(R.drawable.placec2)
                        "AWAY" -> seat60.setImageResource(R.drawable.placec5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(60)?.status) {
                        "EMPTY" -> seat61.setImageResource(R.drawable.ellipse22)
                        "USED" -> seat61.setImageResource(R.drawable.placec2)
                        "AWAY" -> seat61.setImageResource(R.drawable.placec5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(61)?.status) {
                        "EMPTY" -> seat62.setImageResource(R.drawable.ellipse22)
                        "USED" -> seat62.setImageResource(R.drawable.placec2)
                        "AWAY" -> seat62.setImageResource(R.drawable.placec5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(62)?.status) {
                        "EMPTY" -> seat63.setImageResource(R.drawable.ellipse22)
                        "USED" -> seat63.setImageResource(R.drawable.placec2)
                        "AWAY" -> seat63.setImageResource(R.drawable.placec5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(63)?.status) {
                        "EMPTY" -> seat64.setImageResource(R.drawable.place1)
                        "USED" -> seat64.setImageResource(R.drawable.place2)
                        "AWAY" -> seat64.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(64)?.status) {
                        "EMPTY" -> seat65.setImageResource(R.drawable.place1)
                        "USED" -> seat65.setImageResource(R.drawable.place2)
                        "AWAY" -> seat65.setImageResource(R.drawable.place5)
                        else -> Log.e("error","error")
                    }
                    when (data?.get(65)?.status) {
                        "EMPTY" -> seat66.setImageResource(R.drawable.ellipse22)
                        "USED" -> seat66.setImageResource(R.drawable.placec2)
                        "AWAY" -> seat66.setImageResource(R.drawable.placec5)
                        else -> Log.e("error","error")
                    }
                }
            }
            override fun onFailure(call: Call<Array<Seats>>, t: Throwable) {
                Log.e("좌석현황", "${t.localizedMessage}")
            }
        })

        reportBtn.setOnClickListener {
            if(cnt == 0){
                Toast.makeText(applicationContext, "신고 할 좌석을 선택해주세요.", Toast.LENGTH_SHORT).show()
            }else if(cnt > 1){
                Toast.makeText(applicationContext, "신고는 한 좌석씩 가능합니다.", Toast.LENGTH_SHORT).show()
            }else{
                val service = retrofit.create(ReportService::class.java)
                val reportSeat = ReportSeats(id.toLong())
                service.report(token,reportSeat).enqueue(object : Callback<Object> {
                    override fun onResponse(call: Call<Object>, response: Response<Object>) {
                        if(response.code()==400){
                            //val jsonObject = JSONObject(response.errorBody().toString());
                            Log.d("에러 ", "${response.errorBody()?.string()!!}")
                        }
                        else{
                            Log.d("좌석신고" , "${response.raw()}")
                            Log.d("좌석신고" , "${response.body()}")
                            reportSuccessLink()
                        }
                    }

                    override fun onFailure(call: Call<Object>, t: Throwable) {
                        Log.e("좌석신고", "${t.localizedMessage}")
                    }
                })
            }
        }
        dialogBtn.setOnClickListener {
            // Dialog만들기
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.desk_state_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("좌석상태 확인표")
            mBuilder.show()
        }

    }
    fun reportSuccessLink(){
        var intent = Intent(this, AppReportSuccess::class.java) //다음 화면 이동을 위한 intent 객체 생성
        startActivity(intent)
        finish()
    }
}


//@Headers("content-type: application/json", "Authorization: Bearer" + )

interface ReportService{
    /*public open val accessToken: String
        get() = MyApplication.prefs.getString("AccessToken", "")*/

    @Headers("accept: application/json", "content-type: application/json")
    @POST("/seats/report")
    fun report(
        @Header("Authorization") token : String?,
        @Body seats : ReportSeats
    ) : Call<Object>
}

interface DeskState{
    @Headers("accept: application/json", "content-type: application/json")
    @GET("/seats")
    fun desk(
    ) : Call<Array<Seats>>
}
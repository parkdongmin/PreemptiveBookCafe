package com.bignerdranch.android.preemptivebookcafeuser

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import kotlinx.android.synthetic.main.app_login.*
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class AppLogIn : AppCompatActivity() {

    lateinit var mainNumTextBox:EditText
    lateinit var mainPwTextBox:EditText
    lateinit var loginBtn:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_login)

        mainNumTextBox = findViewById(R.id.mainNumTextBox)
        mainPwTextBox = findViewById(R.id.mainPwTextBox)
        loginBtn = findViewById(R.id.loginBtn)



        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.36.156.88:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SignService::class.java)

        val RefreshToken = MyApplication.prefs.getString("RefreshToken", "")
        val AccessToken = MyApplication.prefs.getString("AccessToken", "")
        val idStrGet = MyApplication.prefs.getString("idStr", "")


        if( RefreshToken != "" && AccessToken != ""){
            var intent = Intent(this, AppMain::class.java) //다음 화면 이동을 위한 intent 객체 생성
            Log.e("RefreshToken", "${MyApplication.prefs.getString("RefreshToken", "")}")
            Log.e("AccessToken", "${MyApplication.prefs.getString("AccessToken", "")}")
            intent.putExtra("id",idStrGet)
            startActivity(intent)
            finish()
        }

        loginBtn.setOnClickListener {
            val idStr = mainNumTextBox.text.toString()
            val pwStr = mainPwTextBox.text.toString()
            val user = LoginRequest(idStr.toLong(), pwStr)
            service.login(user).enqueue(object :Callback<Object>{
                override fun onResponse(call: Call<Object>, response: Response<Object>) {
                    val result = response.body()
                    if(response.code()==400){
                        //val jsonObject = JSONObject(response.errorBody().toString());
                        Log.d("에러 ", "${response.errorBody()?.string()!!}")
                    }
                    else{
                        Log.d("로그인" , "${response.raw()}")
                        Log.d("로그인" , "${result}")
                        val jsonObject = JSONTokener(response.body().toString()).nextValue() as JSONObject
                        val refreshToken = jsonObject.getString("RefreshToken")
                        val accessToken = jsonObject.getString("AccessToken")
                        MyApplication.prefs.setString("RefreshToken", "${refreshToken}")
                        MyApplication.prefs.setString("AccessToken", "${accessToken}")
                        MyApplication.prefs.setString("idStr", "${idStr}")
                        Log.d("RefreshToken", "${MyApplication.prefs.getString("RefreshToken", "")}")
                        Log.d("AccessToken", "${MyApplication.prefs.getString("AccessToken", "")}")
                        Log.d("idStr", "${MyApplication.prefs.getString("idStr", "")}")
                        mainLink()
                    }
                }

                override fun onFailure(call: Call<Object>, t: Throwable) {
                    Log.e("로그인", "${t.localizedMessage}")
                }
            })
        }

        joinGoBtn.setOnClickListener {
            var intent = Intent(this, AppRegister::class.java) //다음 화면 이동을 위한 intent 객체 생성
            startActivity(intent)
            finish()
        }
    }
    fun mainLink(){
        var intent = Intent(this, AppMain::class.java) //다음 화면 이동을 위한 intent 객체 생성
        intent.putExtra("id",mainNumTextBox.text.toString())
        startActivity(intent)
        finish()
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
    }
}

interface SignService {
    @Headers("accept: application/json", "content-type: application/json")
    @POST("/user/login")
    fun login(
        /*
        @Field("classNo") registerNumTextBox:String,
        @Field("password") registerPwTextBox:String,
        @Field("email") registerEmailTextBox:String*/
        @Body User :LoginRequest
    ) : Call<Object>
}
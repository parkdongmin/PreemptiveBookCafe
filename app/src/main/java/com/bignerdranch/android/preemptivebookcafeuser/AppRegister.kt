package com.bignerdranch.android.preemptivebookcafeuser

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.app_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*


class AppRegister : AppCompatActivity() {

    lateinit var registerNumTextBox:EditText
    lateinit var registerPwTextBox:EditText
    lateinit var registerEmailTextBox:EditText
    lateinit var registerBtn:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_register)

        registerNumTextBox = findViewById(R.id.registerNumTextBox)
        registerPwTextBox = findViewById(R.id.registerPwTextBox)
        registerEmailTextBox = findViewById(R.id.registerEmailTextBox)
        registerBtn = findViewById(R.id.joinExBtn)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://3.36.156.88:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RegisterService::class.java)

        checkBoxR.setOnCheckedChangeListener{compoundButton, b ->
            if(b)
                registerPwTextBox.inputType = (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
            else
                registerPwTextBox.inputType = (InputType.TYPE_CLASS_TEXT)
        }

        topBackSpace.setOnClickListener {
            AppLogInLink()
        }

        registerBtn.setOnClickListener {
            val idStr = registerNumTextBox.text.toString()
            val pwStr = registerPwTextBox.text.toString()
            val emailStr = registerEmailTextBox.text.toString()
            val fbToken = MyApplication.prefs.getString("fbToken", "")
            val user = RegisterRequest(idStr.toLong(), pwStr, emailStr, fbToken)
            service.register(user).enqueue(object :Callback<Object>{
                override fun onResponse(call: Call<Object>, response: Response<Object>) {
                    if(response.code()==400){
                        Log.d("?????? ", "${response.errorBody()?.string()!!}")
                        Toast.makeText(applicationContext, "?????? ?????? ??????????????????.", Toast.LENGTH_SHORT).show()
                        AppRegisterFailLink()

                    }
                    else{
                        Log.d("????????????" , "${response.raw()}")
                        Log.d("????????????" , "${response.body()}")
                        AppLogInLink()
                    }
                }

                override fun onFailure(call: Call<Object>, t: Throwable) {
                    Log.e("????????????", "${t.localizedMessage}")
                    AppRegisterFailLink()
                }
            })
        }
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
    }

    fun AppLogInLink(){
        var intent = Intent(this, AppLogIn::class.java) //?????? ?????? ????????? ?????? intent ?????? ??????
        startActivity(intent)
        finish()
    }

    fun AppRegisterFailLink(){
        var intent = Intent(this, AppRegister::class.java) //?????? ?????? ????????? ?????? intent ?????? ??????
        startActivity(intent)
        finish()
    }
}

interface RegisterService{
    //@GET("v1/user")  Call<Object> getUserData()
    @Headers("accept: application/json", "content-type: application/json")
    @POST("/user/signUp")
    fun register(
        /*
        @Field("classNo") registerNumTextBox:String,
        @Field("password") registerPwTextBox:String,
        @Field("email") registerEmailTextBox:String */
        @Body User :RegisterRequest
    ) : Call<Object>
}
package com.bignerdranch.android.preemptivebookcafeuser

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.app_register.*
import org.json.JSONObject
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

/*

        val call: Call<*> = userAPIInterface.getUserData()
        call.enqueue(object : Callback<Any?> {
            fun onResponse(call: Call<*>?, response: Response<*>) {
                Log.e(TAG, "body: " + Gson().toJson(response.body()))
            }

            fun onFailure(call: Call<*>?, t: Throwable) {}
        })
*/
        registerBtn.setOnClickListener {
            val idStr = registerNumTextBox.text.toString()
            val pwStr = registerPwTextBox.text.toString()
            val emailStr = registerEmailTextBox.text.toString()
            val user = RegisterRequest(idStr.toLong(), pwStr, emailStr)
            service.register(user).enqueue(object :Callback<Object>{
                override fun onResponse(call: Call<Object>, response: Response<Object>) {
                    if(response.code()==400){
                        //val jsonObject = JSONObject(response.errorBody().toString());
                        Log.d("에러 ", "${response.errorBody()?.string()!!}")
                    }
                    else{
                        Log.d("회원가입" , "${response.raw()}")
                        Log.d("회원가입" , "${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Object>, t: Throwable) {
                    Log.e("회원가입", "${t.localizedMessage}")
                }
            })
        }
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
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
        @Field("email") registerEmailTextBox:String*/
        @Body User :RegisterRequest
    ) : Call<Object>
}
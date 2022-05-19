package com.coufie.menumakananch7.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.coufie.menumakananch7.R
import com.coufie.menumakananch7.model.GetAllUserItem
import com.coufie.menumakananch7.model.RequestUser
import com.coufie.menumakananch7.network.RestoranApi
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register()
    }

    fun register(){
        btn_register.setOnClickListener {

            if(et_register_email.text.isEmpty() || et_register_password.text.isEmpty() || et_register_username.text.isEmpty()){
                Toast.makeText(this@RegisterActivity, "Data belum lengkap", Toast.LENGTH_SHORT).show()
            }else{
                if(et_register_password.text.toString() != et_confirm_password.text.toString()){
                    Toast.makeText(this@RegisterActivity, "Password tidak sama", Toast.LENGTH_SHORT).show()
                }else{
                    val email = et_register_email.text.toString()
                    val password = et_register_password.text.toString()
                    val username = et_register_username.text.toString()

                    postUserRegister(email, password, username)
                }
            }

        }
    }

    fun postUserRegister(email : String, password : String, username : String){
        RestoranApi.instance.postUser(RequestUser(email, password, username) )
            .enqueue(object : retrofit2.Callback<GetAllUserItem>{
                override fun onResponse(
                    call: Call<GetAllUserItem>,
                    response: Response<GetAllUserItem>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(this@RegisterActivity, "Register Berhasil", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    }else{
                        Toast.makeText(this@RegisterActivity, "Register Gagal", Toast.LENGTH_LONG).show()

                    }
                }

                override fun onFailure(call: Call<GetAllUserItem>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Register Gagal", Toast.LENGTH_LONG).show()
                }

            })
    }

}
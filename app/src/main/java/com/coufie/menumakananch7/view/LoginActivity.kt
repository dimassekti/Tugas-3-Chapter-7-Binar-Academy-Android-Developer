package com.coufie.menumakananch7.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coufie.menumakananch7.R
import com.coufie.menumakananch7.datastore.UserManager
import com.coufie.menumakananch7.model.GetAllUserItem
import com.coufie.menumakananch7.network.RestoranApi
import com.coufie.menumakananch7.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    //datastore login
    lateinit var userManager: UserManager

    var email = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userManager = UserManager(this)

        login()
        register()



    }

    fun register(){
        tv_goto_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

   fun login(){
       btn_login.setOnClickListener{

           if(et_input_email.text.isNotEmpty()&&et_input_password.text.isNotEmpty()){
               email = et_input_email.text.toString()
               password = et_input_password.text.toString()

               getUserLogin()
           }else{
               Toast.makeText(this, "Mohon isi Email & Password", Toast.LENGTH_SHORT).show()
           }
       }
   }

    fun getUserLogin(){
        RestoranApi.instance.getUser(email)
            .enqueue(object : retrofit2.Callback<List<GetAllUserItem>>{
                override fun onResponse(
                    call: Call<List<GetAllUserItem>>,
                    response: Response<List<GetAllUserItem>>
                ) {
                    if(response.isSuccessful){
                        if(response.body()?.isEmpty() == true){
                            Toast.makeText(this@LoginActivity, "Akun Belum Terdaftar", Toast.LENGTH_SHORT).show()
                        }else{
                            when {
                                response.body()?.size!! > 1 -> {
                                    Toast.makeText(this@LoginActivity, "Please input your data correctly", Toast.LENGTH_SHORT).show()
                                }
                                email != response.body()!![0].email -> {
                                    Toast.makeText(this@LoginActivity, "Email Belum Terdaftar", Toast.LENGTH_SHORT).show()
                                }
                                password != response.body()!![0].password -> {
                                    Toast.makeText(this@LoginActivity, "Password Belum Sesuai", Toast.LENGTH_SHORT).show()

                                }
                                else -> {
                                    GlobalScope.launch {
                                        userManager.saveLoginData(
                                            response.body()!![0].id,
                                            response.body()!![0].email,
                                            response.body()!![0].password,
                                            response.body()!![0].username
                                        )
                                    }

                                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                                }
                            }
                        }
                    }else{
                        Toast.makeText(this@LoginActivity, "Maaf, Server Sedang Maintenance", Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<List<GetAllUserItem>>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Maaf, Server Sedang Maintenance", Toast.LENGTH_SHORT).show()

                }

            })
    }



}
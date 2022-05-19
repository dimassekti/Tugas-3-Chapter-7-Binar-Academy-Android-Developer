package com.coufie.menumakananch7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.asLiveData
import com.coufie.menumakananch7.datastore.UserManager
import com.coufie.menumakananch7.view.HomeActivity
import com.coufie.menumakananch7.view.LoginActivity

class MainActivity : AppCompatActivity() {

    lateinit var userManager: UserManager
    lateinit var email : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userManager = UserManager(this)

        userManager.userEmail.asLiveData().observe(this, {
            email = it.toString()
        })
        Handler(Looper.getMainLooper()).postDelayed({

            if(email != ""){
                startActivity(Intent(this, HomeActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }


        }, 3000)
    }


}
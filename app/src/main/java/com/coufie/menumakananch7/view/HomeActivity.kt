package com.coufie.menumakananch7.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.coufie.menumakananch7.R
import com.coufie.menumakananch7.datastore.UserManager
import com.coufie.menumakananch7.view.adapter.MenuAdapter
import com.coufie.menumakananch7.viewmodel.MakananViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    lateinit var menuAdapter: MenuAdapter
    lateinit var userManager : UserManager
    lateinit var id : String
    lateinit var username : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userManager = UserManager(this)
        userManager.userUsername.asLiveData().observe(this, {
            this@HomeActivity.username = it.toString()
            introx.setText(username)
        })

        initMenuRecycler()
        favourites()
        logout()


    }

    fun favourites(){
        fab_favourite.setOnClickListener {
            startActivity(Intent(this, MenuFavouriteActivity::class.java))
        }
    }

    fun logout(){
        btn_logout.setOnClickListener {
            GlobalScope.launch {
                userManager.deleteUserData()
            }
            startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
        }
    }

    fun initMenuRecycler(){
        menuAdapter = MenuAdapter (){
            val pindah = Intent(this, DetailMenuActivity::class.java)
            pindah.putExtra("DETAILMENU", it)
            startActivity(pindah)
        }

        rv_menu_makanan.layoutManager = GridLayoutManager(this, 2)
        rv_menu_makanan.adapter = menuAdapter

        initMenuViewModel()
    }

    fun initMenuViewModel(){
        val viewModel = ViewModelProvider(this).get(MakananViewModel::class.java)
        viewModel.getLiveDataMakanann().observe(this, Observer {
            if(it != null){
                menuAdapter.setMenuMakananList(it)
                menuAdapter.notifyDataSetChanged()
            }else{

            }
        })
        viewModel.getDataMakanan()
    }
}
package com.coufie.menumakananch7.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.coufie.menumakananch7.R
import com.coufie.menumakananch7.room.FavouriteMenuDatabase
import com.coufie.menumakananch7.view.adapter.MenuFavouriteAdapter
import kotlinx.android.synthetic.main.activity_menu_favourite.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MenuFavouriteActivity : AppCompatActivity() {

    var favouriteMenuDb : FavouriteMenuDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_favourite)

        favouriteMenuDb = FavouriteMenuDatabase.getInstance(this)
        getFavouriteMenu()
    }

    fun getFavouriteMenu(){

        rv_favouritemenu.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            val listFavMenu = favouriteMenuDb?.favouriteMenuDao()?.getAllFavouriteMenu()
            runOnUiThread{
                if(listFavMenu?.size!! < 1){
                    tv.setText("Favourite-ku masing kosong")
                }else{
                    listFavMenu.let {
                        rv_favouritemenu.adapter = MenuFavouriteAdapter(it!!)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getFavouriteMenu()
    }

    override fun onDestroy() {
        super.onDestroy()
        getFavouriteMenu()
    }
}
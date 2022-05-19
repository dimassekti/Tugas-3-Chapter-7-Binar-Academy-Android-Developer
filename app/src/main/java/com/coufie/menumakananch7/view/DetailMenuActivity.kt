package com.coufie.menumakananch7.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.coufie.menumakananch7.R
import com.coufie.menumakananch7.model.GetAllMenuItem
import com.coufie.menumakananch7.room.FavouriteMenu
import com.coufie.menumakananch7.room.FavouriteMenuDatabase
import kotlinx.android.synthetic.main.activity_detail_menu.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class DetailMenuActivity : AppCompatActivity() {

    var favouriteMenuDb : FavouriteMenuDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_menu)

        favouriteMenuDb = FavouriteMenuDatabase.getInstance(this)

        detailfilm()

        iv_addfavfilm.setOnClickListener{

            GlobalScope.async {

                val detailFavMenu = intent.getParcelableExtra<GetAllMenuItem>("DETAILMENU")

                val harga = detailFavMenu!!.harga
                val nama = detailFavMenu!!.namaMakanan
                val gambar = detailFavMenu!!.gambar
                val deskripsi = detailFavMenu!!.deskripsi
                val id = detailFavMenu!!.id

                runOnUiThread {
                    var checkdata = favouriteMenuDb?.favouriteMenuDao()?.insertFavouriteMenu(
                        FavouriteMenu(id.toInt(),  nama, harga, gambar, deskripsi))
                    if(checkdata != 0.toLong()){
                        Toast.makeText(this@DetailMenuActivity, "Menu Ditambahkan Ke Favourite", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@DetailMenuActivity, "gagal Ditambahkan Ke Favourite", Toast.LENGTH_SHORT).show()
                    }

                }

            }
        }

    }

    fun detailfilm(){
        val detailMenu = intent.getParcelableExtra<GetAllMenuItem>("DETAILMENU")

        tv_menuharga.text = "${detailMenu!!.harga}"
        tv_namamenu.text = "Rp. ${detailMenu!!.namaMakanan}"
        tv_menudeskripsi.text = detailMenu!!.deskripsi
        Glide.with(this).load(detailMenu!!.gambar).into(iv_gambarmenu)
    }

}
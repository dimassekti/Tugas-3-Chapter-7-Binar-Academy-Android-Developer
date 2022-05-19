package com.coufie.menumakananch7.view.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coufie.menumakananch7.R
import com.coufie.menumakananch7.room.FavouriteMenu
import com.coufie.menumakananch7.room.FavouriteMenuDatabase
import com.coufie.menumakananch7.view.MenuFavouriteActivity
import kotlinx.android.synthetic.main.item_menu_favourite.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MenuFavouriteAdapter(val listMenuFavourite : List<FavouriteMenu>) : RecyclerView.Adapter<MenuFavouriteAdapter.ViewHolder>() {

   var favouriteMenuDb : FavouriteMenuDatabase? = null

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuFavouriteAdapter.ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_favourite, parent, false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: MenuFavouriteAdapter.ViewHolder, position: Int) {
        holder.itemView.tv_namamakanan.text = "Rp. ${listMenuFavourite!![position].harga.toString()}"
        holder.itemView.tv_menuharga.text = "${listMenuFavourite!![position].nama_makanan.toString()}"

        this.let {
            Glide.with(holder.itemView.context).load(listMenuFavourite!![position].gambar).into(holder.itemView.iv_gambarmenuu)
        }


        holder.itemView.btn_favdelete.setOnClickListener {
            favouriteMenuDb = FavouriteMenuDatabase.getInstance(it.context)

            val ADBuilder = AlertDialog.Builder(it.context)
                .setTitle("Hapus Data")
                .setMessage("Yakin Hapus?")
                .setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->
                    GlobalScope.async {

                        val deleteResult = favouriteMenuDb?.favouriteMenuDao()?.deleteFavouriteMenu(listMenuFavourite[position])

                        (holder.itemView.context as MenuFavouriteActivity).runOnUiThread {
                            if(deleteResult != null){
                                Toast.makeText(it.context, "Data Berhasil dihapus", Toast.LENGTH_LONG).show()
                                (holder.itemView.context as MenuFavouriteActivity).recreate()
                            }else{
                                Toast.makeText(it.context, "Data Gagal dihapus", Toast.LENGTH_LONG).show()
                            }
                        }

                    }
                }
                .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }
                .show()

        }

    }

    override fun getItemCount(): Int {
        return listMenuFavourite.size
    }

}
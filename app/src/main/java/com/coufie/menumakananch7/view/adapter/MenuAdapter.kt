package com.coufie.menumakananch7.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coufie.menumakananch7.R
import com.coufie.menumakananch7.model.GetAllMenuItem
import kotlinx.android.synthetic.main.item_menu.view.*
import retrofit2.http.GET

class MenuAdapter (private var menuMakananOnClick : (GetAllMenuItem)-> Unit) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    private var menuMakananData : List<GetAllMenuItem>? = null

    fun setMenuMakananList(menuMakananList: List<GetAllMenuItem>){
        this.menuMakananData = menuMakananList
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.ViewHolder {
        val uiMenu = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu, parent, false)

        return ViewHolder(uiMenu)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_harga_makanan.text = "Rp. ${menuMakananData!![position].harga.toString()}"
        holder.itemView.tv_nama_makanan.text = "${menuMakananData!![position].namaMakanan.toString()}"

        this.let {
            Glide.with(holder.itemView.context).load(menuMakananData!![position].gambar).into(holder.itemView.iv_menuimage)
        }

        holder.itemView.menuCard.setOnClickListener {
            menuMakananOnClick(menuMakananData!![position])
        }
    }

    override fun getItemCount(): Int {
        if(menuMakananData == null){
            return 0
        }else{
            return menuMakananData!!.size
        }
    }

}
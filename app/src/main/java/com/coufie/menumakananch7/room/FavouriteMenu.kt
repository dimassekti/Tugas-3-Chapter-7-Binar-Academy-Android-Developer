package com.coufie.menumakananch7.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class FavouriteMenu (
    @PrimaryKey()
    val id: Int?,

    @ColumnInfo(name = "nama_makanan")
    val nama_makanan: String?,

    @ColumnInfo(name = "harga")
    val harga: String?,

    @ColumnInfo(name = "gambar")
    val gambar: String?,

    @ColumnInfo(name = "deskripsi")
    val deskripsi: String?,

) : Parcelable
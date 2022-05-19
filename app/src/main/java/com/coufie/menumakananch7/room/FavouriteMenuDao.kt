package com.coufie.menumakananch7.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavouriteMenuDao {
    @Insert
    fun insertFavouriteMenu(favoriteMenu: FavouriteMenu) : Long

    @Delete
    fun deleteFavouriteMenu(favoriteMenu: FavouriteMenu) : Int

    @Query("SELECT * FROM FavouriteMenu")
    fun getAllFavouriteMenu() : List<FavouriteMenu>

}
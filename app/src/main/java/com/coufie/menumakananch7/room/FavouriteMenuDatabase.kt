package com.coufie.menumakananch7.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavouriteMenu::class], version = 2)

abstract class FavouriteMenuDatabase : RoomDatabase(){

    abstract fun favouriteMenuDao() : FavouriteMenuDao

    companion object{
        private var INSTANCE : FavouriteMenuDatabase? = null
        fun getInstance(context : Context): FavouriteMenuDatabase? {
            if (INSTANCE == null){
                synchronized(FavouriteMenuDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavouriteMenuDatabase::class.java,"FavouriteMenu.db").allowMainThreadQueries().fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}
package com.coufie.menumakananch7.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coufie.menumakananch7.model.GetAllMenuItem
import com.coufie.menumakananch7.network.RestoranApi
import retrofit2.Call
import retrofit2.Response

class MakananViewModel : ViewModel(){

    lateinit var liveDataMakanan : MutableLiveData<List<GetAllMenuItem>>

    init {
        liveDataMakanan = MutableLiveData()
    }

    fun getLiveDataMakanann() : MutableLiveData<List<GetAllMenuItem>>{
        return liveDataMakanan
    }

    fun getDataMakanan(){
        RestoranApi.instance.getAllMenu()
            .enqueue(object : retrofit2.Callback<List<GetAllMenuItem>>{
                override fun onResponse(
                    call: Call<List<GetAllMenuItem>>,
                    response: Response<List<GetAllMenuItem>>
                ) {
                    if(response.isSuccessful){
                        liveDataMakanan.postValue(response.body())
                    }else{
                        liveDataMakanan.postValue((null))
                    }
                }

                override fun onFailure(call: Call<List<GetAllMenuItem>>, t: Throwable) {
                    liveDataMakanan.postValue(null)
                }

            })
    }

}
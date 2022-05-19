package com.coufie.menumakananch7.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coufie.menumakananch7.model.GetAllUserItem
import com.coufie.menumakananch7.network.RestoranApi
import retrofit2.Call
import retrofit2.Response

class UserViewModel : ViewModel() {

    lateinit var liveDataUser : MutableLiveData<List<GetAllUserItem>>

    init{
        liveDataUser = MutableLiveData()
    }

    fun getLiveDataUserr() : MutableLiveData<List<GetAllUserItem>>{
        return liveDataUser
    }

    fun getDataUser(){
        RestoranApi.instance.getAllUser()
            .enqueue(object : retrofit2.Callback<List<GetAllUserItem>>{
                override fun onResponse(
                    call: Call<List<GetAllUserItem>>,
                    response: Response<List<GetAllUserItem>>
                ) {
                    if(response.isSuccessful){
                        liveDataUser.postValue(response.body())
                    }else{
                        liveDataUser.postValue((null))
                    }
                }

                override fun onFailure(call: Call<List<GetAllUserItem>>, t: Throwable) {
                    liveDataUser.postValue((null))
                }

            })
    }

    fun login(email : String){
        RestoranApi.instance.getUser(email)
            .enqueue(object : retrofit2.Callback<List<GetAllUserItem>> {
                override fun onResponse(
                    call: Call<List<GetAllUserItem>>,
                    response: Response<List<GetAllUserItem>>
                ) {
                    if(response.isSuccessful){
                        if(response.body()?.isEmpty() == true){

                        }else{

                        }
                    }
                }

                override fun onFailure(call: Call<List<GetAllUserItem>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

}
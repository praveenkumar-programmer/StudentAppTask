package com.geeks4ever.studentapptask.model.repository

import com.geeks4ever.studentapptask.model.repository.fireBase.FireBaseRepository

object Repository {

    private val database = FireBaseRepository

    val fireBaseUser = database.fireBaseUser
    val loading = database.loading
    val error = database.error

    fun logIn(email : String, password : String){
        database.logIn(email, password)
    }

    fun signUp(email : String, password : String){
        database.signUp(email, password)
    }

    fun logOut(){
        database.logOut()
    }

}
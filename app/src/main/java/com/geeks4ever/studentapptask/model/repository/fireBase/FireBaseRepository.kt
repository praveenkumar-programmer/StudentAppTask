package com.geeks4ever.studentapptask.model.repository.fireBase

import com.google.firebase.database.FirebaseDatabase


object FireBaseRepository {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    val loading = MyFireBaseAuth.loading
    val error = MyFireBaseAuth.error
    val fireBaseUser = MyFireBaseAuth.currentUser








    fun logIn(email : String, password : String){
        MyFireBaseAuth.logIn(email, password)
    }

    fun signUp(email : String, password : String){
        MyFireBaseAuth.signUp(email, password)
    }

    fun logOut(){
        MyFireBaseAuth.logOut()
    }
}
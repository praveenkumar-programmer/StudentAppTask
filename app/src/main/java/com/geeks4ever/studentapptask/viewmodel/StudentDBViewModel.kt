package com.geeks4ever.studentapptask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.geeks4ever.studentapptask.model.repository.Repository

class StudentDBViewModel(application: Application) : AndroidViewModel(application) {

    val loading = Repository.loading
    val error = Repository.error
    val fireBaseUser = Repository.fireBaseUser


}
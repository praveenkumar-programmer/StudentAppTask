package com.geeks4ever.studentapptask.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.geeks4ever.studentapptask.R
import com.geeks4ever.studentapptask.viewmodel.AuthViewModel
import com.geeks4ever.studentapptask.viewmodel.StudentDBViewModel
import kotlinx.android.synthetic.main.home_page.*

class HomePage : AppCompatActivity() {

    private lateinit var viewModel: StudentDBViewModel
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(this.application)
        )[StudentDBViewModel::class.java]

        authViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(this.application)
        )[AuthViewModel::class.java]

        viewModel.fireBaseUser.observeForever { firebaseUser ->
            if (firebaseUser == null) {
                gotoLogin()
            }
        }

        homeRoot.setOnClickListener{
            collapseOptions()
        }

        homeOptionsButton.setOnClickListener{
            expandOptions()
        }

        homeLogOut.setOnClickListener{ logout() }
        homeExit.setOnClickListener{ exit() }

    }

    private fun expandOptions() {
        homeOptionsButton.visibility = View.GONE
        homeOptionsLayout.setBackgroundResource(R.drawable.rounded_bg_white)
        homeLogOut.visibility = View.VISIBLE
        homeExit.visibility = View.VISIBLE
    }

    private fun collapseOptions() {
        homeOptionsButton.visibility = View.VISIBLE
        homeOptionsLayout.setBackgroundResource(0)
        homeLogOut.visibility = View.GONE
        homeExit.visibility = View.GONE
    }


    private fun exit() {
        finish()
    }

    private fun logout() {
        authViewModel.logout()
    }


    private fun gotoLogin() {
        startActivity(Intent(this, AuthPage::class.java))
        finish()
    }
}
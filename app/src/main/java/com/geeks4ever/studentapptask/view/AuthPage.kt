package com.geeks4ever.studentapptask.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.geeks4ever.studentapptask.R
import com.geeks4ever.studentapptask.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.auth_page.*
import java.util.*

class AuthPage : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_page)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(this.application)
        )[AuthViewModel::class.java]
        viewModel.getCurrentUser().observe(this,
            { firebaseUser -> if (firebaseUser != null) gotoHome() })

        authSubText2.setOnClickListener{
            if(authSubText2.text.toString().lowercase(Locale.getDefault()) == "signup") setPageToSignUp()
            else setPageToSignIn()
        }

        authButton.setOnClickListener{
            if(authButton.text.toString().lowercase(Locale.getDefault()) == "sign up") {
                if (authEmailEditText.text.isNullOrEmpty()
                    || authPasswordEdittext.text.isNullOrEmpty()
                    || authPhoneEditText.text.isNullOrEmpty()
                    || authConfirmEditText.text.isNullOrEmpty()
                )
                    Toast.makeText(this, "Please fill in every field!", Toast.LENGTH_SHORT).show()
                else if (authPasswordEdittext.text.toString() != authConfirmEditText.text.toString())
                    Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show()
                else
                    viewModel.signUp(
                        authEmailEditText.text.toString(),
                        authPasswordEdittext.text.toString()
                    )
            }
            else {

                if (authEmailEditText.text.isNullOrEmpty()
                    || authPasswordEdittext.text.isNullOrEmpty())
                    Toast.makeText(this, "Please fill in every field!", Toast.LENGTH_SHORT).show()
                else
                    viewModel.login(
                        authEmailEditText.text.toString(),
                        authPasswordEdittext.text.toString()
                    )
            }
        }

    }

    private fun setPageToSignUp(){

        authCurveBG.setImageResource(R.drawable.semi_rounded_bg_inverted)
        authTitle.text = "SIGN UP"
        authPhoneLayout.visibility = View.VISIBLE
        authConfirmLayout.visibility = View.VISIBLE
        authForgetPass.visibility = View.GONE
        authButton.text = "sign up"
        authSubText.text = "Already have an account?  "
        authSubText2.text = "SignIn"


    }

    private fun setPageToSignIn(){

        authCurveBG.setImageResource(R.drawable.semi_rounded_bg)
        authTitle.text = "SIGN IN"
        authPhoneLayout.visibility = View.GONE
        authConfirmLayout.visibility = View.GONE
        authForgetPass.visibility = View.VISIBLE
        authButton.text = "Log in"
        authSubText.text = "Don't have an account?  "
        authSubText2.text = "SignUp"


    }

    private fun gotoHome() {
        startActivity(Intent(this, HomePage::class.java))
        finish()
    }

}
package com.example.mvvmapp.ui.ui.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mvvmapp.R
import com.example.mvvmapp.ui.MainActivity
import com.example.mvvmapp.ui.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class Login : AppCompatActivity() {
    private var auth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn.setOnClickListener {
            loginUser()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        signup_tv.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
        reset_txt.setOnClickListener {
            startActivity(Intent(this, ForgottenPassword::class.java))
        }

    }
    private fun loginUser(){
        val email = email_et_login.text.toString()
        val pass = password_et_login.text.toString()
        login_progress_bar.visibility = View.VISIBLE
        if (email.isEmpty()){
            Constants.Utils.showToast(this, "Please enter email")
        }
        if (pass.isEmpty()){
            Constants.Utils.showToast(this, "Please enter password")
        }
        if (email.isEmpty() && pass.isEmpty()){
            Constants.Utils.showToast(this, "Please Fill the fields")
        }
        auth.signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    Constants.Utils.showToast(this, "Registration Successful")
                }
            }.addOnFailureListener {
                login_progress_bar.visibility = View.GONE
                Constants.Utils.showToast(this,"Error: $it")
            }
    }

}
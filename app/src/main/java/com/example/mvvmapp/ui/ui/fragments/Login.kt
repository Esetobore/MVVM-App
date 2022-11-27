package com.example.mvvmapp.ui.ui.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mvvmapp.R
import com.example.mvvmapp.ui.MainActivity
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

    }
    private fun loginUser(){
        val email = email_et_login.text.toString()
        val pass = password_et_login.text.toString()
        if (email.isEmpty()){
            toast("please enter email")
        }
        if (pass.isEmpty()){
            toast("please enter password")
        }
        auth.createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    toast("Registration Successful")
                }
            }.addOnFailureListener {
                progress_bar.visibility = View.GONE
                toast("Error: $it")
            }
    }

    private fun toast(m:String){
        Toast.makeText(this, m, Toast.LENGTH_LONG).show()
    }

}
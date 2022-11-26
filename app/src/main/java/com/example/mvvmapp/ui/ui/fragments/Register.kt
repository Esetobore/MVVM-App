package com.example.mvvmapp.ui.ui.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mvvmapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    private var auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register_btn.setOnClickListener {
            registerUser()
        }
        signin_tv.setOnClickListener {
            onBackPressed()
        }

    }
    private fun registerUser(){
        val email = email_et.text.toString()
        val pass = password_et.text.toString()
        val firstname = FN_et.text.toString()
        val lastname = LN_et.text.toString()
        if (email.isEmpty()){
            toast("please enter email")
        }
        if (pass.isEmpty()){
            toast("please enter password")
        }
        if (firstname.isEmpty()){
            toast("please enter name")
        }
        if(lastname.isEmpty()){
            toast("please enter last name")
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
package com.example.mvvmapp.ui.ui.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mvvmapp.R
import com.example.mvvmapp.ui.MainActivity
import com.example.mvvmapp.ui.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    private var auth : FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var db: DatabaseReference
    private val email = email_et.text.toString()
    private val pass = password_et.text.toString()
    private val firstname = FN_et.text.toString()
    private val lastname = LN_et.text.toString()
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
                userInfo()
                toast("Registration Successful")
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }.addOnFailureListener {
                progress_bar.visibility = View.GONE
                toast("Error: $it")
        }
    }
    private fun toast(m:String){
        Toast.makeText(this, m, Toast.LENGTH_LONG).show()
    }

    private fun userInfo(){
        db = FirebaseDatabase.getInstance().getReference("Users")
        val user = User(firstname, lastname)
        db.child(firstname).setValue(user)
    }
}
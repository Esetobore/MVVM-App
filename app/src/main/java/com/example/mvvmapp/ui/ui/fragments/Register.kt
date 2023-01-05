package com.example.mvvmapp.ui.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmapp.R
import com.example.mvvmapp.ui.MainActivity
import com.example.mvvmapp.ui.User
import com.example.mvvmapp.ui.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_register.*


class Register : AppCompatActivity() {
    private var auth : FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private val email = email_et.text.toString()
    private val pass = password_et.text.toString()
    private val firstname = FN_et.text.toString()
    private val lastname = LN_et.text.toString()
    lateinit var imageUri: Uri
    val Uid = auth.currentUser?.uid
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register_btn.setOnClickListener {
            registerUser()
        }
        signin_tv.setOnClickListener {
            onBackPressed()
        }
        profile_btn.setOnClickListener {
            profileImage()
        }

    }
    private fun registerUser(){
        uploadImage()
        register_progress_bar.visibility = View.VISIBLE
        if (email.isEmpty()){
            Constants.Utils.showToast(this,"Please enter email")
        }
        if (pass.isEmpty()){
            Constants.Utils.showToast(this,"Please enter password")
        }
        if (firstname.isEmpty()){
            Constants.Utils.showToast(this,"Please enter First Name")
        }
        if(lastname.isEmpty()){
            Constants.Utils.showToast(this,"Please enter Last Name")
        }

        auth.createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener { task->
            if (task.isSuccessful){
                register_progress_bar.visibility = View.GONE
                userInfo()
                Constants.Utils.showToast(this, "Registration Successful")
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }.addOnFailureListener {
                register_progress_bar.visibility = View.GONE
                Constants.Utils.showToast(this,"Error: $it")
        }
    }


    private fun userInfo(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        val user = User(firstname, lastname, email)
        databaseReference.child(lastname).setValue(user)
    }
    private fun uploadImage(){
        storageReference = FirebaseStorage.getInstance().getReference("UserImg/"+auth.currentUser?.uid+".jpg")
        storageReference.putFile(imageUri)
    }
    private fun profileImage(){
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, 100)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK){
            imageUri = data?.data!!
        }
    }

}
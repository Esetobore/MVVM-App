package com.example.mvvmapp.ui.ui.fragments


import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mvvmapp.R
import com.example.mvvmapp.ui.User
import com.example.mvvmapp.ui.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.File

class ProfileFragment: Fragment(R.layout.fragment_profile) {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var auth : FirebaseAuth
    private lateinit var uid : String
    private lateinit var user : User
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        if(uid.isNotEmpty()){
            getUserData()
        }
    }
    private fun getUserData(){
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User::class.java)!!
                name_is_here_profile.text = user.firstname
                firstname_Profile.text = user.firstname
                Lastname_profile.text = user.lastname
                profile_email.text = user.email
                getProfileImg()

            }
            override fun onCancelled(error: DatabaseError) {
                Constants.Utils.showToast(activity,"Error $error")
            }

        })
    }
    private fun getProfileImg(){
        storageReference = FirebaseStorage.getInstance().reference.child("UserImg/$uid.jpg")
        val localFile = File.createTempFile("tempImage", "jpg")
        storageReference.getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            profile_image.setImageBitmap(bitmap)
        }.addOnFailureListener{
            Constants.Utils.showToast(activity,"Error: $it")
        }
    }

}
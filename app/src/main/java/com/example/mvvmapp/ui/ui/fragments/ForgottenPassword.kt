
package com.example.mvvmapp.ui.ui.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mvvmapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgotten_password.*

class ForgottenPassword : AppCompatActivity() {
    private var auth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotten_password)

        reset_btn.setOnClickListener{
            resetPass()
        }
    }
    private fun resetPass(){
        val email = email_et_reset.text.toString()
        reset_progress_bar.visibility = View.VISIBLE
        if (email.isEmpty()){
            com.example.mvvmapp.ui.utils.Constants.Utils.showToast(this, "Please enter email")
        }
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                reset_progress_bar.visibility = View.GONE
                com.example.mvvmapp.ui.utils.Constants.Utils.showToast(this, "Check Registered email for Reset Email")
                startActivity(Intent(this,Login::class.java))
            }
            .addOnFailureListener {
                com.example.mvvmapp.ui.utils.Constants.Utils.showToast(this,"Error: $it")
                reset_progress_bar.visibility = View.GONE
            }

    }
}
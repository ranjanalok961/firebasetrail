package com.example.firebasetrail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class Regsiter : AppCompatActivity() {
//    private lateinit var signInRequest : GoogleSignInOptions

    private lateinit var emailInput : EditText
    private lateinit var passwordInput : EditText
    private lateinit var register : Button
    private lateinit var login : Button
    private lateinit var registerWithEmail : Button
    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_regsiter)
        emailInput = findViewById(R.id.RegEmail)
        passwordInput = findViewById(R.id.RegPass)
        register = findViewById(R.id.regReg)
        login = findViewById(R.id.regLogin)
        registerWithEmail = findViewById(R.id.regWithGoogle)
        auth = FirebaseAuth.getInstance()

        register.setOnClickListener {
            var email = emailInput.text.toString()
            var password = passwordInput.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                Register(email, password)
            } else {
                Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        login.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        registerWithEmail.setOnClickListener {
            var email = emailInput.text.toString()
            registerWithEmail()
        }

//        var signInRequest = BeginSignInRequest.builder()
//            .setGoogleIdTokenRequestOptions(
//                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    // Your server's client ID, not your Android client ID.
//                    .setServerClientId(getString(R.string.your_web_client_id))
//                    // Only show accounts previously used to sign in.
//                    .setFilterByAuthorizedAccounts(true)
//                    .build())
//            .build()
    }
    private fun Register(email : String , pass : String){
        auth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener { task ->
            Toast.makeText(this,"Register Complete", Toast.LENGTH_LONG).show()
        }.addOnFailureListener { task ->
            if(task.message == "The email address is already in use by another account."){
                Toast.makeText(this,"Already Register", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }else{
                if(task.message?.contains("Password must contain an upper case character") == true){
                    Toast.makeText(this,"Password must contain an upper case character", Toast.LENGTH_LONG).show()
                }else if(task.message?.contains("Password must contain a non-alphanumeric character") == true) {
                    Toast.makeText(this,"Password must contain a non-alphanumeric character", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Register NotCompleted", Toast.LENGTH_LONG).show()
                    Log.d("Loin","${task.cause}")
                    Log.d("Loin","${task.message}")
                }

            }

        }
    }
    private fun signInGoogle
    private fun registerWithEmail(){

    }
}
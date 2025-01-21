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
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var emailInput : EditText
    private lateinit var passwordInput : EditText
    private lateinit var register : Button
    private lateinit var login : Button
    private lateinit var loginWithEmail : Button
    private lateinit var auth:FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        emailInput = findViewById(R.id.LoginEmail)
        passwordInput = findViewById(R.id.LoginPass)
        register = findViewById(R.id.Register)
        login = findViewById(R.id.Login)
        loginWithEmail = findViewById(R.id.loginWithEmail)
        auth = FirebaseAuth.getInstance()

        register.setOnClickListener {
            startActivity(Intent(this,Regsiter::class.java))
            finish()
        }

        login.setOnClickListener {
            var email = emailInput.text.toString()
            var password = passwordInput.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                Login(email, password)
            } else {
                Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun Login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {task->
            Toast.makeText(this,"SignIn Completed ${auth.currentUser?.uid}",Toast.LENGTH_LONG).show()
        }.addOnFailureListener { task ->

//            Log.d("Loin","${task.cause}")
//            Log.d("Loin","${task.message}")
//            Toast.makeText(this,"First Register",Toast.LENGTH_LONG).show()
            if(task.cause == null){
                Toast.makeText(this,"First Register",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,Regsiter::class.java))
                finish()
            }
        }
    }


}
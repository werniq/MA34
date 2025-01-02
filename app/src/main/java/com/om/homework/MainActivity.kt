package com.om.homework

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private var emailText: EditText? = null
    private var passwordText: EditText? = null
    private var loginButton: Button? = null
    private var auth: FirebaseAuth? = null

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        auth = FirebaseAuth.getInstance()

        emailText = findViewById(R.id.email_input)
        passwordText = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)

        loginButton?.setOnClickListener(View.OnClickListener { v: View? ->
            val email = emailText?.text.toString()
            val password = passwordText?.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(
                    this@MainActivity,
                    "Email and Password cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }

            val TAG = "MainActivity"
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth?.currentUser
                        Toast.makeText(
                            this@MainActivity,
                            "Successfully authenticated.",
                            Toast.LENGTH_SHORT,
                        ).show()

                        // Correct usage of context
                        Toast.makeText(this@MainActivity, "Login successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity, ItemsListView::class.java)
                        startActivity(intent)
                        Toast.makeText(this@MainActivity, "Forwarding...", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            this@MainActivity,
                            "Authentication failed: ${task.exception?.message}",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        })
    }
}

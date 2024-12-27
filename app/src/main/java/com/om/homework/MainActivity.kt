package com.om.homework

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
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
            }
            val TAG = "A";
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth?.currentUser
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        })
    }
}
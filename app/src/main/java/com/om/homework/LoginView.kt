package com.om.homework

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginView : FrameLayout {

    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var loginButton: Button
    private lateinit var auth: FirebaseAuth;

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttrs: Int) : super(
        context, attrs, defStyleAttrs) {
        init(context)
    }

    private fun init(context: Context) {
        auth = Firebase.auth

        LayoutInflater.from(context).inflate(R.layout.login_layout, this, true)

        emailText = findViewById(R.id.email_input)
        passwordText = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
        }
    }
}

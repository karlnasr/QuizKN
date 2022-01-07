package com.example.quizkn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val regbtn = findViewById<Button>(R.id.reg_btn)
        regbtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val currentuser = auth.currentUser
        if (currentuser != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
        login()
    }

    private fun login() {

        sign_btn.setOnClickListener {

            if (TextUtils.isEmpty(emailfield.text.toString())) {
                emailfield.error = "Please enter your email"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(pwfield.text.toString())) {
                pwfield.error = "Please enter password"
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(emailfield.text.toString(), pwfield.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Login failed, please try again! ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }
    }
}
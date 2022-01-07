package com.example.quizkn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database!!.reference.child("Profile")

        register()

        val signbtn = findViewById<Button>(R.id.sign_btn)

        signbtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun register() {

        reg_btn.setOnClickListener {
            if (TextUtils.isEmpty(fnamefield.text.toString())) {
                fnamefield.error = "Please enter your First Name "
                return@setOnClickListener
            } else if (TextUtils.isEmpty(lnamefield.text.toString())) {
                lnamefield.error = "Please enter Last Name "
                return@setOnClickListener
            } else if (TextUtils.isEmpty(emailfield.text.toString())) {
                emailfield.error = "Please enter your email "
                return@setOnClickListener
            } else if (TextUtils.isEmpty(pwfield.text.toString())) {
                pwfield.error = "Please enter your password "
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(
                emailfield.text.toString(),
                pwfield.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val currentUser = auth!!.currentUser!!.uid
                        val currentUSerDb = databaseReference!!.child((currentUser))
                        currentUSerDb.child("firstname").setValue(fnamefield.text.toString())
                        currentUSerDb.child("lastname").setValue(lnamefield.text.toString())

                        Toast.makeText(
                            this@RegisterActivity,
                            "Registration Successful",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registration failed, please try again!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}
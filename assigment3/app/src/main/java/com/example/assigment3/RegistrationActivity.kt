// RegistrationActivity.kt
package com.example.assigment3
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegistrationActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (validateInputs(name, email, password)) {
                val user = User(name, email, password)
                navigateToLogin(user)
            }
        }
    }

    private fun validateInputs(name: String, email: String, password: String): Boolean {
        if (name.isEmpty()) {
            etName.error = "Name is required"
            return false
        }

        if (email.isEmpty()) {
            etEmail.error = "Email is required"
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Please enter a valid email"
            return false
        }

        if (password.isEmpty()) {
            etPassword.error = "Password is required"
            return false
        }

        if (password.length < 6) {
            etPassword.error = "Password must be at least 6 characters"
            return false
        }

        return true
    }

    private fun navigateToLogin(user: User) {
        if (validateInputs(user.name, user.email, user.password)) {
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java).apply {
                putExtra("USER_DATA", user)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }

            startActivity(intent)
            finish()
        }
    }
}
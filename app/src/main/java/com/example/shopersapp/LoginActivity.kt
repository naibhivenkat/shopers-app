package com.example.shopersapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.shopersapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.shopersapp.network.LoginRequest
import com.example.shopersapp.network.LoginResponse

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val request = LoginRequest(username, password)
                ApiClient.apiService.loginUser(request).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful && response.body() != null) {
                            val userType = response.body()!!.user_type
                            val userId = response.body()!!.user_id

                            val intent = when (userType) {
                                "customer" -> Intent(this@LoginActivity, CustomerHomeActivity::class.java)
                                "shopkeeper" -> Intent(this@LoginActivity, ShopkeeperHomeActivity::class.java)
                                else -> null
                            }

                            intent?.putExtra("user_id", userId)
                            intent?.let { startActivity(it) }
                        } else {
                            Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Login failed: ${t.message}", Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

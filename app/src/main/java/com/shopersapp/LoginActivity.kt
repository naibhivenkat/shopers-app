package com.shopersapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.shopersapp.network.ApiClient
import com.shopersapp.network.LoginRequest
import com.shopersapp.network.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.editUsername)
        val password = findViewById<EditText>(R.id.editPassword)
        val loginBtn = findViewById<Button>(R.id.buttonLogin)

        loginBtn.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()

            ApiClient.apiService.login(LoginRequest(user, pass)).enqueue(object :
                Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful && response.body()?.status == "success") {
                        val intent = if (response.body()?.role == "customer") {
                            Intent(this@LoginActivity, CustomerHomeActivity::class.java)
                        } else {
                            Intent(this@LoginActivity, ShopkeeperHomeActivity::class.java)
                        }
                        intent.putExtra("userId", response.body()?.user_id)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}

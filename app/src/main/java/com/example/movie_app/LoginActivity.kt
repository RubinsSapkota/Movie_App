package com.example.movie_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// LoginActivity: This activity handles user login functionality.
// It provides a user interface for entering credentials (username and password)
// and interacts with the server via an API to authenticate the user.
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Bind views to their respective elements in the layout
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        // Set an OnClickListener on the login button to trigger login attempt when clicked
        loginButton.setOnClickListener {
            // Retrieve the username and password from the EditTexts
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Check if username or password is empty, if so, show a Toast message
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter your username and password", Toast.LENGTH_SHORT).show()
            } else {
                // Create a LoginRequest object with the entered credentials
                val loginRequest = LoginRequest(username, password)

                // Make an API call using Retrofit to perform user login
                RetrofitInstance.api.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                    // Handle the response from the server when the call is successful
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful) {
                            // Extract the keypass (authentication token) from the response body
                            val keypass = response.body()?.keypass
                            if (keypass != null) {
                                // Create an Intent to navigate to DashboardActivity, passing the keypass
                                val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                                intent.putExtra("KEYPASS", keypass)

                                // Clear LoginActivity from the backstack to prevent the user from returning to it
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            }
                        } else {
                            // Show a Toast message if the login credentials are invalid
                            Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                        }
                    }

                    // Handle failures such as network issues or server errors
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        // Show a Toast message if there's an error during the API call
                        Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}

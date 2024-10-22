package com.example.movie_app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// RetrofitInstance: This object is a singleton that provides an instance of Retrofit
// configured to interact with the API at the specified BASE_URL.
object RetrofitInstance {
    // The base URL for the API requests. This is the endpoint of the backend server.
    private const val BASE_URL = "https://nit3213-api-h2b3-latest.onrender.com"

    // Lazy initialization of the ApiService using Retrofit.
    // The Retrofit instance is only created when it's accessed for the first time (lazy initialization).
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)  // Set the base URL for all API requests
            .addConverterFactory(GsonConverterFactory.create())  // Use Gson to convert JSON responses
            .build()  // Build the Retrofit instance
            .create(ApiService::class.java)  // Create and return an implementation of the ApiService interface
    }
}

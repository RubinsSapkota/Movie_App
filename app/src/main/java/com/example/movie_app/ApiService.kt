package com.example.movie_app

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// ApiService interface: Defines the API endpoints for our application using Retrofit
// Retrofit automatically generates the network request code for us based on this interface.

interface ApiService {

    /**
     * Sends a POST request to the /footscray/auth endpoint to perform user login.
     * @param loginRequest - the login credentials wrapped in a LoginRequest object.
     * @return Call<LoginResponse> - asynchronously returns a response containing login details.
     *
     * Note: The endpoint is specific to the Footscray location, so adjust it if the API changes.
     */
    @POST("/footscray/auth")  // Adjusted endpoint for Footscray location authentication
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    /**
     * Sends a GET request to the /dashboard/{keypass} endpoint to fetch dashboard data.
     * @param keypass - a path parameter representing the user's unique keypass (authentication token or ID).
     * @return Call<DashboardResponse> - asynchronously returns the dashboard data response.
     */
    @GET("/dashboard/{keypass}")  // Retrieve dashboard data using a keypass as a path parameter
    fun getDashboardData(@Path("keypass") keypass: String): Call<DashboardResponse>
}

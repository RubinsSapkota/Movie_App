package com.example.movie_app

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Represents a login request containing the user's username and password.
// This object will be sent as part of the request body in the login API call.
data class LoginRequest(
    val username: String,  // Username entered by the user
    val password: String   // Password entered by the user
)

// Represents the response from the login API.
// This contains a keypass, which is typically a token used to authenticate future requests.
data class LoginResponse(
    val keypass: String  // Keypass or authentication token returned by the server on successful login
)

// Represents the response from the dashboard API, which includes a list of entities (e.g., movies)
// and a total count of entities available.
data class DashboardResponse(
    val entities: List<Entity>,  // A list of Entity objects (e.g., movies) fetched from the server
    val entityTotal: Int         // Total number of entities returned by the API
)

// Entity class represents a movie or similar entity with details such as title, director, genre, etc.
// It is annotated with @Parcelize to make it Parcelable, enabling it to be passed between activities via Intents.
@Parcelize
data class Entity(
    val title: String,
    val director: String,
    val genre: String,
    val releaseYear: Int,
    val description: String
) : Parcelable  // Implements Parcelable to allow easy passing between activities

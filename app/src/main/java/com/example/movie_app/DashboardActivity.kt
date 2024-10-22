package com.example.movie_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent

// DashboardActivity: This activity is responsible for displaying a list of entities (movies, etc.)
// fetched from the server, and allows navigation to details of each item by clicking on it.

class DashboardActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var entityAdapter: EntityAdapter
    private var entities: List<Entity> = emptyList()  // List to hold entities (e.g., movies)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize the RecyclerView and set its layout manager
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)  // Use linear layout for the list

        // Retrieve the keypass from the intent (passed from the login or previous activity)
        val keypass = intent.getStringExtra("KEYPASS")

        // Check if the keypass was passed correctly, and fetch the dashboard data if it exists
        if (keypass != null) {
            fetchDashboardData(keypass)
        } else {
            // Show a Toast message if keypass is missing
            Toast.makeText(this, "Keypass is missing!", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Fetch dashboard data using the provided keypass.
     * The data will be retrieved from the API and displayed in the RecyclerView.
     * @param keypass: A string representing the user's keypass (used to authenticate the request).
     */
    private fun fetchDashboardData(keypass: String) {
        // Make an API call using Retrofit to fetch dashboard data
        RetrofitInstance.api.getDashboardData(keypass).enqueue(object : Callback<DashboardResponse> {
            // Called when the API call is successful
            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                if (response.isSuccessful) {
                    // Populate the entities list with the response data
                    entities = response.body()?.entities ?: emptyList()

                    // Debugging: Show a Toast message indicating how many items were received
                    if (entities.isNotEmpty()) {
                        Toast.makeText(this@DashboardActivity, "Received ${entities.size} items", Toast.LENGTH_SHORT).show()

                        // Set up the adapter for the RecyclerView and handle item click events
                        entityAdapter = EntityAdapter(entities) { entity ->
                            // Create an intent to navigate to the DetailsActivity when an item is clicked
                            val intent = Intent(this@DashboardActivity, DetailsActivity::class.java)
                            intent.putExtra("ENTITY", entity)  // Pass the entity to the next activity
                            startActivity(intent)  // Start the DetailsActivity
                        }
                        // Attach the adapter to the RecyclerView
                        recyclerView.adapter = entityAdapter
                    } else {
                        // Show a message if no entities are available to display
                        Toast.makeText(this@DashboardActivity, "No items to display", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Handle unsuccessful response (e.g., server error)
                    Toast.makeText(this@DashboardActivity, "Failed to load dashboard data", Toast.LENGTH_SHORT).show()
                }
            }

            // Called when the API call fails (e.g., network issues)
            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                // Show an error message with the reason for failure
                Toast.makeText(this@DashboardActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

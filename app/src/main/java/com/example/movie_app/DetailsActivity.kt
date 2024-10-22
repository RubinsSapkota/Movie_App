package com.example.movie_app

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

// DetailsActivity: This activity displays detailed information about a specific entity (e.g., a movie)
// that was clicked on in the DashboardActivity. It shows details such as the title, director, genre,
// release year, description, and an image related to the entity.

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Set up the toolbar to allow back navigation in the app
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // Enable the Up (back) button in the toolbar

        // Retrieve the Entity object passed from DashboardActivity using the Intent
        val entity = intent.getParcelableExtra<Entity>("ENTITY")

        // Bind views (ImageView and TextViews) to their respective layout elements
        val entityImageView = findViewById<ImageView>(R.id.entityImageView)
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val directorTextView = findViewById<TextView>(R.id.directorTextView)
        val genreTextView = findViewById<TextView>(R.id.genreTextView)
        val releaseYearTextView = findViewById<TextView>(R.id.releaseYearTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)

        // Populate the TextViews and ImageView with the details from the Entity object
        entity?.let {
            titleTextView.text = "Title: ${it.title}"  // Set the movie title
            directorTextView.text = "Director: ${it.director}"  // Set the movie director
            genreTextView.text = "Genre: ${it.genre}"  // Set the movie genre
            releaseYearTextView.text = "ReleaseYear: ${it.releaseYear}"  // Set the release year
            descriptionTextView.text = "Description: ${it.description}"  // Set the movie description

            // Set the image corresponding to the entity's title. If the title matches one of the known movie titles,
            // the corresponding drawable resource will be set for the ImageView.
            when (it.title) {
                "The Godfather" -> entityImageView.setImageResource(R.drawable.the_godfather)  // Set image for The Godfather
                "Pulp Fiction" -> entityImageView.setImageResource(R.drawable.pulp_fiction)  // Set image for Pulp Fiction
                "The Shawshank Redemption" -> entityImageView.setImageResource(R.drawable.the_shawshank_redemption)  // Set image for The Shawshank Redemption
                "Schindler's List" -> entityImageView.setImageResource(R.drawable.schindlers_list)  // Set image for Schindler's List
                "Forrest Gump" -> entityImageView.setImageResource(R.drawable.forrest_gump)  // Set image for Forrest Gump
                "The Matrix" -> entityImageView.setImageResource(R.drawable.the_matrix)  // Set image for The Matrix
                "Citizen Kane" -> entityImageView.setImageResource(R.drawable.citizen_kane)  // Set image for Citizen Kane
                else -> entityImageView.setImageResource(R.drawable.ic_profile)  // Default image if no match is found
            }
        }
    }

    // Handle the toolbar's Up (back) button click event
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {  // Handle the "home" button (back navigation)
                finish()  // Close DetailsActivity and go back to DashboardActivity
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

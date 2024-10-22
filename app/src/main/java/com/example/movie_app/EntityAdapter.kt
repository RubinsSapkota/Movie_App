package com.example.movie_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// EntityAdapter: A custom adapter class for the RecyclerView that binds a list of Entity objects
// to the RecyclerView's views, enabling item display and click handling in a list.
class EntityAdapter(private val entities: List<Entity>, private val onClick: (Entity) -> Unit) :
    RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    // EntityViewHolder: A ViewHolder class that holds references to the views in each list item.
    // This pattern improves performance by reusing views and avoiding repeated findViewById() calls.
    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.property1TextView)  // TextView for displaying entity title
    }

    // Called when RecyclerView needs a new ViewHolder to display an item.
    // This inflates the item layout (item_entity.xml) and creates a new EntityViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    // Called by RecyclerView to display the data at the specified position in the list.
    // It binds the data from the Entity object to the views in the EntityViewHolder.
    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entities[position]
        holder.titleTextView.text = entity.title  // Bind the entity's title to the TextView

        // Set an OnClickListener to handle clicks on each list item.
        // This triggers the onClick lambda function passed from the DashboardActivity.
        holder.itemView.setOnClickListener {
            onClick(entity)  // Pass the clicked entity to the onClick function
        }
    }

    // Returns the total number of items in the RecyclerView (i.e., the size of the entities list).
    override fun getItemCount(): Int {
        return entities.size
    }
}

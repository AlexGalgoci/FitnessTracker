package com.example.fitnesstracker.ui.exercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.R

class ExerciseAdapter(private val exercises: List<Exercise>) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentItem = exercises[position]
        holder.textViewName.text = currentItem.name
        holder.textViewDuration.text = currentItem.duration.toString()
    }

    override fun getItemCount() = exercises.size

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.exerciseName)
        val textViewDuration: TextView = itemView.findViewById(R.id.exerciseDuration)
    }
}

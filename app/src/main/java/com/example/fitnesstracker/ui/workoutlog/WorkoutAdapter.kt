package com.example.fitnesstracker.ui.workoutlog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.R

class WorkoutAdapter(private val workouts: List<Workout>) :
    RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout, parent, false)
        return WorkoutViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val currentWorkout = workouts[position]
        holder.workoutName.text = currentWorkout.name
        holder.workoutDuration.text = currentWorkout.duration.toString()
    }

    override fun getItemCount() = workouts.size

    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val workoutName: TextView = itemView.findViewById(R.id.textViewWorkoutName)
        val workoutDuration: TextView = itemView.findViewById(R.id.textViewWorkoutDuration)
    }
}

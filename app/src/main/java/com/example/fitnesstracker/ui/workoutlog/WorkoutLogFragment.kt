package com.example.fitnesstracker.ui.workoutlog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fitnesstracker.R
import com.example.fitnesstracker.databinding.FragmentWorkoutLogBinding
import com.example.fitnesstracker.ui.socialfeed.Post
import com.example.fitnesstracker.ui.socialfeed.SocialFeedViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class WorkoutLogFragment : Fragment() {

    private var _binding: FragmentWorkoutLogBinding? = null
    private val binding get() = _binding!!
    private val workouts = mutableListOf<Workout>()
    private val adapter = WorkoutAdapter(workouts)

    private val socialFeedViewModel: SocialFeedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener {
            showAddWorkoutDialog()
        }
    }

    private fun showAddWorkoutDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_workout, null)
        val workoutNameInput = dialogView.findViewById<EditText>(R.id.editTextWorkoutName)
        val workoutDurationInput = dialogView.findViewById<EditText>(R.id.editTextWorkoutDuration)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add Workout")
            .setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val name = workoutNameInput.text.toString()
                val duration = workoutDurationInput.text.toString().toInt()
                val newWorkout = Workout(name, duration)
                workouts.add(newWorkout)
                adapter.notifyDataSetChanged()
                dialog.dismiss()

                // Adding to social feed
                val caption = "Workout: $name"
                val content = "Duration: $duration minutes"
                val imageUri = "" // No image for workout
                socialFeedViewModel.addPost(Post(caption, content, imageUri))
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

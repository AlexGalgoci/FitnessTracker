package com.example.fitnesstracker.ui.workoutlog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkoutLogViewModel : ViewModel() {

    private val _workouts = MutableLiveData<MutableList<Workout>>().apply { value = mutableListOf() }
    val workouts: LiveData<MutableList<Workout>> = _workouts

    fun addWorkout(workout: Workout) {
        _workouts.value?.add(workout)
        _workouts.value = _workouts.value // Trigger LiveData update
    }
}

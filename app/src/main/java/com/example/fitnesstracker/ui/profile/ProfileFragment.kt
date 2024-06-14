package com.example.fitnesstracker.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitnesstracker.databinding.DialogEditProfileBinding
import com.example.fitnesstracker.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner // Use view's lifecycle
        binding.viewModel = profileViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editButton.setOnClickListener {
            showEditProfileDialog()
        }
    }

    private fun showEditProfileDialog() {
        val dialogBinding = DialogEditProfileBinding.inflate(layoutInflater)
        dialogBinding.editTextProfileName.setText(profileViewModel.name.value)
        dialogBinding.editTextProfileEmail.setText(profileViewModel.email.value)

        AlertDialog.Builder(requireContext())
            .setTitle("Edit Profile")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") { dialog, _ ->
                val name = dialogBinding.editTextProfileName.text.toString()
                val email = dialogBinding.editTextProfileEmail.text.toString()
                profileViewModel.updateProfile(name, email)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

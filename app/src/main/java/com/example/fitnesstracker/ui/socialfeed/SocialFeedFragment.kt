package com.example.fitnesstracker.ui.socialfeed

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnesstracker.R
import com.example.fitnesstracker.databinding.FragmentSocialFeedBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SocialFeedFragment : Fragment() {

    private var _binding: FragmentSocialFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var socialFeedViewModel: SocialFeedViewModel
    private lateinit var adapter: PostAdapter
    private var selectedImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSocialFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        socialFeedViewModel = ViewModelProvider(this).get(SocialFeedViewModel::class.java)
        adapter = PostAdapter(socialFeedViewModel.posts.value ?: mutableListOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener {
            showAddPostDialog()
        }

        socialFeedViewModel.posts.observe(viewLifecycleOwner) {
            adapter.updatePosts(it)
        }

        // Check for permissions
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Request permissions
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), REQUEST_CODE)
        }
    }

    private val REQUEST_CODE = 1

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            selectedImageUri = result.data?.data
        }
    }

    private fun showAddPostDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_post, null)
        val captionInput = dialogView.findViewById<EditText>(R.id.editTextPostCaption)
        val contentInput = dialogView.findViewById<EditText>(R.id.editTextPostContent)
        val imageViewPost = dialogView.findViewById<ImageView>(R.id.imageViewPost)
        val selectImageButton = dialogView.findViewById<Button>(R.id.buttonSelectImage)

        selectImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickImageLauncher.launch(intent)
        }

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add Post")
            .setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val caption = captionInput.text.toString()
                val content = contentInput.text.toString()
                val imageUri = selectedImageUri?.toString()
                socialFeedViewModel.addPost(Post(caption, content, imageUri))
                dialog.dismiss()
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

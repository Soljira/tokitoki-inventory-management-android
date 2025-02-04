package com.example.tokitokiinventorymanagementandroid.manager.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.tokitokiinventorymanagementandroid.databinding.FragmentEditProfileBinding

class EditProfileBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    override fun onStart() {
        super.onStart()
        val bottomSheetDialog = dialog as? BottomSheetDialog
        bottomSheetDialog?.behavior?.peekHeight = 5000
    }

    private fun setupUI() {
        binding.doneButton.setOnClickListener {
            saveChanges()
            dismiss()
        }
    }

    private fun saveChanges() {
        val email = binding.emailEditText.text.toString().trim()
        val name = binding.nameEditText.text.toString().trim()
        val phone = binding.phoneEditText.text.toString().trim()

        if (email.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            binding.emailEditText.error = if (email.isEmpty()) "Required" else null
            binding.nameEditText.error = if (name.isEmpty()) "Required" else null
            binding.phoneEditText.error = if (phone.isEmpty()) "Required" else null
            return
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.example.tokitokiinventorymanagementandroid.manager.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tokitokiinventorymanagementandroid.databinding.FragmentReportBugBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReportBugBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentReportBugBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBugBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        binding.submitBugButton.setOnClickListener {
            if (validateInputs()) {
                saveBugReport()
                dismiss()
            }
        }
    }

    private fun validateInputs(): Boolean {
        val email = binding.emailEditText.text.toString().trim()
        val concern = binding.concernEditText.text.toString().trim()

        var isValid = true

        if (email.isEmpty()) {
            binding.emailEditText.error = "Email is required"
            isValid = false
        }

        if (concern.isEmpty()) {
            binding.concernEditText.error = "Please describe your concern"
            isValid = false
        }

        return isValid
    }

    private fun saveBugReport() {
        val email = binding.emailEditText.text.toString().trim()
        val concern = binding.concernEditText.text.toString().trim()
        Toast.makeText(requireContext(), "Bug report submitted successfully!", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.tama.driver.home.settings

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tama.driver.R
import com.tama.driver.databinding.FragmentSettingsBinding
import com.tama.driver.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        _binding?.also { it.viewModel = settingsViewModel }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsViewModel.itemSelection.observe(viewLifecycleOwner) {event->event.getContentIfNotHandled()?.let {
            startActivity(Intent(requireContext(), it.cls))
        }
        }
        binding.logout.setOnClickListener {
            showLogoutDialog()
        }
        settingsViewModel.getDataForSettings(requireContext())
        binding.logoutTxt.setOnClickListener { showLogoutDialog() }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireActivity()).setMessage(getString(R.string.verify_logout))
            .setNegativeButton(getString(R.string.txt_ok)
        ) { dialog, which ->
                dialog.dismiss()
                startActivity(Intent(requireActivity(),LoginActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                })
                requireActivity().finishAffinity()
            }
            .setPositiveButton(getString(R.string.txt_cancel)
            ) { dialog, which ->
                dialog.dismiss()
            }.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
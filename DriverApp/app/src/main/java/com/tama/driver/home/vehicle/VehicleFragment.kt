package com.tama.driver.home.vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tama.driver.databinding.FragmentVechicalsBinding
import com.tama.driver.util.showToast

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleFragment : Fragment() {

    private var _binding: FragmentVechicalsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val viewModel: VehicleViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVechicalsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        viewModel.errorState.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.root.showToast(it, Toast.LENGTH_SHORT)       //show error
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
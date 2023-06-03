package com.tama.car_center.home.vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tama.car_center.util.showToast
import com.tama.syarah.databinding.FragmentHistoryVechicalsBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehiclesHistoryFragment : Fragment() {

    private var _binding: FragmentHistoryVechicalsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val viewModel: VehiclesHistoryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistoryVechicalsBinding.inflate(inflater, container, false)
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
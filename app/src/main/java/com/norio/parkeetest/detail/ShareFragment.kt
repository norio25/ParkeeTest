package com.norio.parkeetest.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.norio.parkeetest.databinding.FragmentShareBinding

/**
 * Created by Norio on 5/18/2023.
 */
class ShareFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentShareBinding? = null
private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShareBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageButton.setOnClickListener { dismiss() }
    }
}
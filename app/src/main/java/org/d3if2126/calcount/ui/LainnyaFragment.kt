package org.d3if2126.calcount.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if2126.calcount.R
import org.d3if2126.calcount.databinding.FragmentLainnyaBinding

class LainnyaFragment : Fragment(){
    private lateinit var binding: FragmentLainnyaBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentLainnyaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
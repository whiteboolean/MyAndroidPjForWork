package com.example.myandroidpjforwork.ui.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.myandroidpjforwork.R
import com.example.myandroidpjforwork.databinding.FragmentHomeBinding
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialFadeThrough

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        exitTransition = Hold()
//        exitTransition = MaterialFadeThrough()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val directions = R.id.action_navigation_home_to_navigation_dashboard
//        val extras = FragmentNavigatorExtras(_binding!!.button1 to "end_container_1")



        _binding?.button1?.setOnClickListener {
//            findNavController().navigate(directions,
//                null,null,extras)

            findNavController().navigate(R.id.action_navigation_home_to_navigation_dashboard)
        }

        _binding?.button2?.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_notifications)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
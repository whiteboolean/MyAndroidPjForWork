package com.example.myandroidpjforwork.ui.ui.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myandroidpjforwork.databinding.FragmentDashboardBinding
import com.example.myandroidpjforwork.ui.MainActivity

class DashboardFragment : Fragment() {

    private val TAG = "DashboardFragment"

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.d(TAG, "onCreateView: ")
        binding.recyclerView.apply {
            adapter = DashBoardListAdapter(dashboardViewModel.list.value ?: mutableListOf())
            layoutManager = LinearLayoutManager(context)
        }


        binding.button4.setOnClickListener {
            startActivity(Intent(activity,MainActivity::class.java))
        }


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")

        ( binding.recyclerView.adapter as DashBoardListAdapter ).list  = mutableListOf()
        (binding.recyclerView.adapter as DashBoardListAdapter).notifyDataSetChanged()


    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: ")
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
        _binding = null
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            Log.d(TAG, "onHiddenChanged: 隐藏")
        } else {
            Log.d(TAG, "onHiddenChanged: 显示")
        }
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        Log.d(TAG, "setUserVisibleHint: isVisibleToUser $isVisibleToUser")

    }

}
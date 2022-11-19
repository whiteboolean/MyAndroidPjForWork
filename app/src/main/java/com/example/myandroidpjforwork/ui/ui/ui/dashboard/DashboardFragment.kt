package com.example.myandroidpjforwork.ui.ui.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myandroidpjforwork.databinding.FragmentDashboardBinding
import com.example.myandroidpjforwork.ui.MainActivity
import java.util.Timer
import kotlin.concurrent.timerTask

class DashboardFragment : Fragment() {

    private val TAG = "DashboardFragment"

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var rcvAdapter: DashBoardListAdapter


    //停止
    val SCROLL_STATE_IDLE = 0

    //正在被外部拖拽,一般为用户正在用手指滚动
    val SCROLL_STATE_DRAGGING = 1

    //自动滚动开始
    val SCROLL_STATE_SETTLING = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.d(TAG, "onCreateView: ")
        binding.recyclerView.let {
            rcvAdapter = DashBoardListAdapter(dashboardViewModel.list.value ?: mutableListOf())
            it.adapter = rcvAdapter
            it.layoutManager = LinearLayoutManager(context)
            it.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    println("newState:${newState}")
                    when (newState) {
                        SCROLL_STATE_IDLE -> {
                            rcvAdapter.setScrollState(SCROLL_STATE_IDLE)
                        }

                        SCROLL_STATE_DRAGGING -> {
                            rcvAdapter.setScrollState(SCROLL_STATE_DRAGGING)
                        }

                        SCROLL_STATE_SETTLING -> {
                            rcvAdapter.setScrollState(SCROLL_STATE_SETTLING)
                        }
                    }
                }
            })

            val timerTask = timerTask {
                activity?.runOnUiThread {
                    rcvAdapter.notifyDataSetChanged()
                }
            }
            val timer = Timer()
            timer.schedule(timerTask, 0, 1000)
        }




        binding.button4.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
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

//        (binding.recyclerView.adapter as DashBoardListAdapter).list = mutableListOf()
//        (binding.recyclerView.adapter as DashBoardListAdapter).notifyDataSetChanged()


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
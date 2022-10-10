package com.example.myandroidpjforwork.ui.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import com.example.myandroidpjforwork.R
import com.example.myandroidpjforwork.databinding.FragmentDashboardLayoutBinding

//import com.example.myandroidpjforwork.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
//
//    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
//    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        sharedElementEnterTransition = MaterialContainerTransform()
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val dashboardViewModel =
//            ViewModelProvider(this).get(DashboardViewModel::class.java)
//
//        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
////        new AsyncLayoutInflater(this).inflate(R.layout.activity_main, null, new AsyncLayoutInflater.OnInflateFinishedListener() {
////        @Override public void onInflateFinished(@NonNull View view, int resid, @Nullable ViewGroup parent)
////        { setContentView(view); //...... } });
//
//        AsyncLayoutInflater(requireContext()).inflate(R.layout.fragment_dashboard,null,object:AsyncLayoutInflater.OnInflateFinishedListener{
//            override fun onInflateFinished(view: View, resid: Int, parent: ViewGroup?) {
//
//            }
//
//        })
//
//
//        binding.layer.setOnClickListener {
//            Toast.makeText(requireContext(), "点击了layer", Toast.LENGTH_SHORT).show()
//        }
//        return root
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

//         inflate loading view
        val final_view = inflater.inflate(R.layout.fragment_loading_view, container, false)
        // get progressbar
        var progressBar = final_view.findViewById<ProgressBar>(R.id.cpb)
        // initialize async layout inflater
        val asyncLayoutInflater = context?.let { AsyncLayoutInflater(it) }
        // inflate other layout
        asyncLayoutInflater?.inflate(
            R.layout.fragment_dashboard_layout,
            null
        ) { view, resid, parent ->
            // when layout loaded show...
            progressBar.postDelayed({
                progressBar.visibility = View.GONE
                (final_view as? ViewGroup)?.addView(view) // add view to already inflated view
            },0)
        }
//        val final_view = FragmentDashboardLayoutBinding.inflate(layoutInflater).root

        return final_view
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        _binding = null
    }
}
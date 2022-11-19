package com.example.myandroidpjforwork.ui.ui.ui.dashboard

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myandroidpjforwork.databinding.ItemRcvBinding

class DashBoardListAdapter(var list: MutableList<String>) :
    RecyclerView.Adapter<DashBoardListAdapter.ViewHolder>() {
    private val TAG = "DashBoardListAdapter"

//    //ViewHolder类将子项布局中所有控件绑定为一个对象，该对象包含子项布局的所有控件
//    static class ViewHolder extends RecyclerView.ViewHolder {
//        Button button;
//        public static int anInt = 0;
//
//        public ViewHolder(View view) {
//            //父类构造函数
//            super(view);
//            //获取RecyclerView布局的子项布局中的所有控件id,本次实验只有TextView这一种控件
//            button = (Button) view.findViewById(R.id.button);
//            anInt++;
//            Log.d("TextAdpter:","ViewHolder:" + anInt);
//        }

    /**
     * 默认滑动状态
     */
    private var scrollState: Int = 0

    fun setScrollState(scrollState: Int) {
        this.scrollState = scrollState
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder: ")
        return ViewHolder(
            ItemRcvBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: scrollState:${scrollState}")
        if (scrollState != 0) {
            return
        }
        Log.d(TAG, "疯狂刷新页面刷新页面")

    }

    override fun getItemCount(): Int {
        return if (list.isEmpty()) {
            0
        } else {
            list.size
        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}
package joandersongoncalves.example.flickrbrowser

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener (
    context: Context,
    recyclerView: RecyclerView,
    private val listener: OnRecyclerClickListener
) : RecyclerView.SimpleOnItemTouchListener() {

    private val TAG = "RecyclerItemClickListen"

    interface OnRecyclerClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    private val gestureDetector = GestureDetectorCompat(context, object: GestureDetector.SimpleOnGestureListener() {

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.d(TAG, "onSingleTapUp called")

            val childView = recyclerView.findChildViewUnder(e.x,e.y)
            Log.d(TAG, ".onSingleTap calling listener.onItemClick")
            if (childView != null) {
                listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
            }
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            Log.d(TAG, "onLongPress called")
            val childView = recyclerView.findChildViewUnder(e.x,e.y)
            Log.d(TAG, ".onLongPress calling listener.onItemLongClick")
            if (childView != null) {
                listener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView))
            }
        }
    })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG, "onInterceptTouchEvent called")
        val result = gestureDetector.onTouchEvent(e)
        Log.d(TAG, ".onInterceptTouchEvent returning: $result")
        //return super.onInterceptTouchEvent(rv, e)
        return result
    }
}

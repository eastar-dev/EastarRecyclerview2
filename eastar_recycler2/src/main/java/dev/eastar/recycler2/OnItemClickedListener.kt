package dev.eastar.recycler2

import android.content.Context
import android.view.*
import android.widget.Checkable
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

@Suppress("unused")
fun RecyclerView.addOnItemClickedListener(callback: (view: View, parent: View, position: Int) -> Unit) {
    addOnItemTouchListener(OnItemClickedListener(context, callback))
}

class OnItemClickedListener(context: Context, private val mListener: (view: View, parent: View, position: Int) -> Unit) : RecyclerView.OnItemTouchListener {
    private var mGestureDetector: GestureDetectorCompat = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent?) = true
    }).apply {
        setIsLongpressEnabled(false)
        setOnDoubleTapListener(null)
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView = view.findChildViewUnder(e.x, e.y)

        if (childView != null && mGestureDetector.onTouchEvent(e)) {
            var callbackView: View = childView
            (childView as? ViewGroup)?.let {
                val childChildView = it.findChildViewUnder(e.x - it.left, e.y - it.top)
                childChildView?.run {
                    if (isEnabled && (childChildView.isClickable || childChildView is Checkable || childChildView.isLongClickable))
                        callbackView = childChildView
                }
            }
            val position = view.getChildLayoutPosition(childView)
            mListener.invoke(callbackView, childView, position)
            childView.playSoundEffect(SoundEffectConstants.CLICK)
            return true
        }
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

    private fun ViewGroup.findChildViewUnder(x: Float, y: Float): View? {
        val count: Int = childCount
        for (i in count - 1 downTo 0) {
            val child: View = getChildAt(i)
            val translationX = child.translationX
            val translationY = child.translationY
            if (x >= child.left + translationX && x <= child.right + translationX && y >= child.top + translationY && y <= child.bottom + translationY) {
                return child
            }
        }
        return null
    }
}
package epost.android.mitake.com.fbloginkotlinsample.adapter

import android.content.Context
import android.graphics.PointF
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class ChildViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {


    var downP = PointF()
    var curP = PointF()

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        curP.x = ev!!.x
        curP.y = ev.y

        if (ev.action == MotionEvent.ACTION_DOWN) {
            downP.x = ev.x
            downP.y = ev.y
            //此句代碼是為了通知他的父ViewPager現在進行的是本控件的操作，不要對我的操作進行干擾
            parent.requestDisallowInterceptTouchEvent(true)
        }

        if (ev.action == MotionEvent.ACTION_MOVE) {
            parent.requestDisallowInterceptTouchEvent(true)
        }
        return super.onTouchEvent(ev)
    }
}
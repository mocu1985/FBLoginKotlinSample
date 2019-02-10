package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.ruling.ui.rulingdetail

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableBoolean
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.MotionEvent
import android.view.View
import epost.android.mitake.com.fbloginkotlinsample.databinding.RulingVoteFragmentBinding

class RulingVoteViewModel : ViewModel() {
    var isPress1 = ObservableBoolean()
    var isPress2 = ObservableBoolean()
    lateinit var binding: RulingVoteFragmentBinding
    lateinit var cxt: Context
    var progress = 0

    companion object {
        var IS_PRESS = 0
        var NO_PRESS = 1
        var POSITIVE = 3
        var NEGATIVE = 4
    }


    //view1碰觸監聽
    var pressListener1 = View.OnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isPress1.set(true)
                progress = 0
                var msg = Message()
                msg.what = IS_PRESS
                msg.arg1 = POSITIVE
                handler.sendMessage(msg)
            }
            MotionEvent.ACTION_UP -> {
                isPress1.set(false)
            }
        }
        true
    }


    //view2碰觸監聽
    var pressListener2 = View.OnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isPress2.set(true)
                progress = 0
                var msg = Message()
                msg.what = IS_PRESS
                msg.arg1 = NEGATIVE
                handler.sendMessage(msg)
            }
            MotionEvent.ACTION_UP -> {
                isPress2.set(false)
            }
        }
        true
    }

    //實作背景長按漸變
    var handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg!!.what) {
                IS_PRESS -> {
                    if (progress != 100) {
                        progress++
                        var message = Message()
                        if (msg.arg1 == POSITIVE) {
                            binding.tranView1.progress = progress
                            message.arg1 = POSITIVE
                            message.what = if (isPress1.get()) IS_PRESS else NO_PRESS
                        } else {
                            binding.tranView2.progress = progress
                            message.arg1 = NEGATIVE
                            message.what = if (isPress2.get()) IS_PRESS else NO_PRESS
                        }

                        this.sendMessageDelayed(message, 7)
                    } else {
                        //TODO 投票完成
                        Log.d("***", "ok")
                    }
                }

                NO_PRESS -> {
                    binding.tranView1.progress = 0
                    binding.tranView2.progress = 0
                    Log.d("***", "NO_PRESS")
                }
            }
        }
    }

}

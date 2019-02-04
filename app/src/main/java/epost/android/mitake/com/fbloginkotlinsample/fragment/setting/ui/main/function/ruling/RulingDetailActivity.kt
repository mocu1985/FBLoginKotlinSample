package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.ruling

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.adapter.RulingDetailAdapter
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.ruling.ui.rulingdetail.RulingDetailFragment
import epost.android.mitake.com.fbloginkotlinsample.framework.TitleBarParentActivity
import kotlinx.android.synthetic.main.activity_share_titlebar_content.*


class RulingDetailActivity : TitleBarParentActivity(), ViewPager.OnPageChangeListener {


    private lateinit var ivPoints: Array<ImageView?>

    override fun initTitleBar() {
        title_bar.centerTextView.text = "申訴明細"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, RulingDetailFragment.newInstance())
//                .commitNow()
//        }

        container.visibility = View.GONE


        initView()
    }

    private fun initView() {
        var rulingInfo = GlobalProperties.rulingObj.rulingInfo
        var fragmentList = ArrayList<Fragment>()
        var index = 0

        //添加舉證分頁
        rulingInfo.detailList.forEach {
            fragmentList.add(RulingDetailFragment(index))
            index++
        }

        var adapter = RulingDetailAdapter(supportFragmentManager, fragmentList)
        view_pager.adapter = adapter

        //添加分頁頁籤紅點
        ivPoints = arrayOfNulls(rulingInfo.detailList.size)
        for (i in ivPoints.indices) {
            val imageView = ImageView(this)
            // 設置圖片的寬高
            imageView.layoutParams = ViewGroup.LayoutParams(10, 10)
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.this_dot)
            } else {
                imageView.setBackgroundResource(R.drawable.other_dots)
            }
            ivPoints[i] = imageView
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                )
            )
            layoutParams.leftMargin = 20// 設置點點點view的左邊距
            layoutParams.rightMargin = 20// 設置點點點view的右邊距
            points.addView(imageView, layoutParams)
        }

        view_pager.addOnPageChangeListener(this)

    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        for (i in ivPoints.indices) {
            if (i == position) {
                ivPoints[i]!!.setBackgroundResource(R.drawable.this_dot)
            } else {
                ivPoints[i]!!.setBackgroundResource(R.drawable.other_dots)
            }
        }
    }

}

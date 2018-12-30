package epost.android.mitake.com.fbloginkotlinsample.framework

import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hjq.bar.OnTitleBarListener
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.firstcreate.FirstProfileFragment
import kotlinx.android.synthetic.main.fragment_parent_activity.*

class FragmentParentActivity : AppCompatActivity() {

    var isEditID = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_parent_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FirstProfileFragment.instance)
                .commitNow()
        }

        initView()
    }

    private fun initView() {
        title_bar.onTitleBarListener = object : OnTitleBarListener {
            override fun onRightClick(v: View?) {
//                if (!isEditID) {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.container, FirstProfileFragment.instance)
//                        .commitNow()
//
//
//                    replaceTitle()
////                    isEditID = true
//                } else {
//                    createAccount()
//                }

                FirstProfileFragment.instance.viewModel.createAccount(FirstProfileFragment.instance.binding)
            }

            override fun onTitleClick(v: View?) {
            }

            override fun onLeftClick(v: View?) {
            }

        }
    }


    private fun replaceTitle() {
        title_bar.title = getString(R.string.profile_check_title)
        title_bar.leftView.text = getString(R.string.pass_title)
        title_bar.rightView.text = getString(R.string.done_title)
    }


    override fun onBackPressed() {
        ActivityCompat.finishAffinity(this)
    }

}

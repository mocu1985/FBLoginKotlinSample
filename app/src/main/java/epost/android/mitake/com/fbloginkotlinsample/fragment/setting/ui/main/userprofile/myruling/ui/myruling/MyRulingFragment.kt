package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.userprofile.myruling.ui.myruling

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.databinding.MyRulingFragmentBinding

//我的申訴單
class MyRulingFragment : Fragment() {
    companion object {

        fun newInstance() = MyRulingFragment()
    }

    private lateinit var viewModel: MyRulingViewModel

    private lateinit var binding: MyRulingFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.my_ruling_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(MyRulingViewModel::class.java)

        viewModel.binding = binding
        viewModel.act = activity!!
        binding.model = viewModel

        viewModel.initView()
        viewModel.loadRulingsData()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
//        viewModel.initData()
    }

}

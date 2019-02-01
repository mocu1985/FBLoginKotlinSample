package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.ruling

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.databinding.RulingNewListFramentFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentActivity
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentFragment

class RulingNewListFrament : ParentFragment() {
    companion object {


        fun newInstance() = RulingNewListFrament()
    }
    private lateinit var viewModel: RulingNewListFramentViewModel

    private lateinit var binding: RulingNewListFramentFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.ruling_new_list_frament_fragment, container, false)

        viewModel = ViewModelProviders.of(this).get(RulingNewListFramentViewModel::class.java)
        viewModel.binding = binding
        viewModel.act = (activity as ParentActivity?)!!

        viewModel.initView()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    override fun initData() {
        viewModel?.loadRulingsData()
    }


}

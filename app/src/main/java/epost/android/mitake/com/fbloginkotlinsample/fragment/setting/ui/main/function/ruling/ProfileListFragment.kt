package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.ruling

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.databinding.ProfileListFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentFragment
import epost.android.mitake.com.fbloginkotlinsample.viewmodel.ProfileListViewModel


class ProfileListFragment : ParentFragment() {
    companion object {
        fun newInstance() =
            ProfileListFragment()
    }

    private lateinit var viewModel: ProfileListViewModel
    private lateinit var binding: ProfileListFragmentBinding

    override fun initData() {
//        initItem()
        viewModel.loadAccountsData(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =
                DataBindingUtil.inflate(inflater, R.layout.profile_list_fragment, container, false)

        viewModel = ViewModelProviders.of(this).get(ProfileListViewModel::class.java)
        viewModel.binding = binding
        viewModel.initView(context!!)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}

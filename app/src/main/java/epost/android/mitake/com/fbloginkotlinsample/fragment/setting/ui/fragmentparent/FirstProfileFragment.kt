package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.fragmentparent

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.databinding.FirstProfileFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.FragmentParentActivity

class FirstProfileFragment : Fragment() {

    companion object {
        val instance: FirstProfileFragment by lazy { FirstProfileFragment() }
    }

    lateinit var viewModel: FirstProfileViewModel
    lateinit var binding: FirstProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.first_profile_fragment, container, false)

        viewModel = ViewModelProviders.of(this).get(FirstProfileViewModel::class.java)

        viewModel.account = GlobalProperties.account

        binding.model = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.activity = context as FragmentParentActivity

    }
}

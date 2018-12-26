package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.fragmentparent

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.databinding.UserIdprofileFragmentBindingImpl

class UserIDProfileFragment : Fragment() {

    companion object {
        fun newInstance() = UserIDProfileFragment()
    }

    private lateinit var viewModel: UserIdprofileViewModel
    private lateinit var binding: UserIdprofileFragmentBindingImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.user_idprofile_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserIdprofileViewModel::class.java)
        // TODO: Use the ViewModel

        Log.d("*****", GlobalProperties.account.userInfo.name)
    }

}

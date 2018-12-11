package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.fragmentparent

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R

class FirstProfileFragment : Fragment() {

    companion object {
        fun newInstance() = FirstProfileFragment()
    }

    private lateinit var viewModel: FirstProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FirstProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

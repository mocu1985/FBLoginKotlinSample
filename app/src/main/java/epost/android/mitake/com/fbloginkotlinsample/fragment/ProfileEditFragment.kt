package epost.android.mitake.com.fbloginkotlinsample.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.databinding.FragmentProfileEditBinding
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentFragment
import epost.android.mitake.com.fbloginkotlinsample.viewmodel.ProfileEditViewModel

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProfileEditFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProfileEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProfileEditFragment : ParentFragment() {
    lateinit var viewModel: ProfileEditViewModel
    lateinit var binding: FragmentProfileEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initData() {
        initDatabase()
    }

    private fun initDatabase() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_edit, container, false)

        viewModel = ViewModelProviders.of(this).get(ProfileEditViewModel::class.java)

        viewModel.account = GlobalProperties.account

        binding.btnConfirm.setOnClickListener {
            if (viewModel.isEdit.get()!!) {
                viewModel.doUpdate(binding)
            }
            viewModel.setBtnText()
        }

        binding.model = viewModel

        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
    }
}

package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.userprofile

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.databinding.FragmentProfileEditBinding
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentFragment
import epost.android.mitake.com.fbloginkotlinsample.viewmodel.ProfileEditViewModel

/**
 *個人資訊
 */
class ProfileEditFragment : ParentFragment() {
    lateinit var viewModel: ProfileEditViewModel
    lateinit var binding: FragmentProfileEditBinding
    val items = arrayOf("我的交易", "我的申訴單")

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

        binding.model = viewModel
        viewModel.cxt = context!!
        viewModel.binding = binding

        addItem()

        return binding.root
    }

    private fun addItem() {
        binding.itemList.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, items)
        viewModel.addItemClick()
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

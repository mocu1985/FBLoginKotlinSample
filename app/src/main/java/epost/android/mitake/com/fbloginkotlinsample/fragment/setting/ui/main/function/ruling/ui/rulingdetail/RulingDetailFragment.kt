package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.ruling.ui.rulingdetail

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.databinding.RulingDetailFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.viewmodel.RulingDetailViewModel

@SuppressLint("ValidFragment")
class RulingDetailFragment : Fragment {

    private lateinit var viewModel: RulingDetailViewModel
    private lateinit var binding: RulingDetailFragmentBinding
    var position = 0

    constructor(position: Int) : super() {
        this.position = position
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.ruling_detail_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(RulingDetailViewModel::class.java)

        binding.model = viewModel
        viewModel.position = position

        binding.tvDetail.movementMethod = ScrollingMovementMethod()

        return binding.root
    }

}

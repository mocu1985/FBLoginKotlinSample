package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.ruling.ui.rulingdetail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.databinding.RulingVoteFragmentBinding

//申訴單投票
class RulingVoteFragment : Fragment() {

    companion object {
        fun newInstance() = RulingVoteFragment()
    }

    private lateinit var viewModel: RulingVoteViewModel
    private lateinit var binding: RulingVoteFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.ruling_vote_fragment, container, false)

        viewModel = ViewModelProviders.of(this).get(RulingVoteViewModel::class.java)
        viewModel.cxt = context!!
        viewModel.binding = binding

        binding.tranView1.setOnTouchListener(viewModel.pressListener1)
        binding.tranView2.setOnTouchListener(viewModel.pressListener2)

        binding.model = viewModel

        return binding.root
    }

    override fun onStop() {
        super.onStop()
    }
}

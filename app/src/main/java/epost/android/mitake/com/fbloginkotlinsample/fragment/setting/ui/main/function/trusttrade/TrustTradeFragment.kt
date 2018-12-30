package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.trusttrade

import android.arch.lifecycle.ViewModelProviders
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.BR
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.databinding.FragmentTrustTradeBinding
import epost.android.mitake.com.fbloginkotlinsample.viewmodel.TrustTradeViewModel

class TrustTradeFragment : Fragment() {

    companion object {
        fun newInstance() = TrustTradeFragment()
    }

    lateinit var viewModel: TrustTradeViewModel
    lateinit var binding: FragmentTrustTradeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trust_trade, container, false)

        viewModel = ViewModelProviders.of(this).get(TrustTradeViewModel::class.java)

        viewModel.viewData = ViewData()
        viewModel.cxt = activity!!

        binding.model = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    class ViewData : BaseObservable() {

        @get:Bindable
        var uuid: String = ""
            set(value) {
                field = value
                notifyPropertyChanged(BR.uuid)
            }

        @get:Bindable
        var score: String = ""
            set(value) {
                field = value
                notifyPropertyChanged(BR.score)
            }

        @get:Bindable
        var note: String = ""
            set(value) {
                field = value
                notifyPropertyChanged(BR.note)
            }

    }

}

package epost.android.mitake.com.fbloginkotlinsample.data

import android.databinding.BaseObservable
import android.databinding.Bindable
import epost.android.mitake.com.fbloginkotlinsample.BR

//信任交易物件
class TrustTradeObject: BaseObservable {

    lateinit var trustInfo: TrustTradeInfo

    constructor()

    @get:Bindable
    var orderId: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.orderId)
        }
}
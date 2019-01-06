package epost.android.mitake.com.fbloginkotlinsample.data

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.google.firebase.Timestamp
import epost.android.mitake.com.fbloginkotlinsample.BR

class RulingInfo : BaseObservable {

    constructor()

    //有效期限
    @get:Bindable
    var endTime: String = (Timestamp.now().seconds + 432000L).toString()
        set(value) {
            field = value
            notifyPropertyChanged(BR.endTime)
        }

    //對應交易單號
    @get:Bindable
    var orderId: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.orderId)
        }

    //爭議單系統時間
    @get:Bindable
    var systemTime: Timestamp = Timestamp.now()
        set(value) {
            field = value
            notifyPropertyChanged(BR.systemTime)
        }

    //正向得票
    @get:Bindable
    var positive: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.positive)
        }

    //負向得票
    @get:Bindable
    var negative: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.negative)
        }

}
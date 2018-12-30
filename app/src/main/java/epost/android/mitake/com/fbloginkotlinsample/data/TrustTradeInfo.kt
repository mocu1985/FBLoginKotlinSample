package epost.android.mitake.com.fbloginkotlinsample.data

import android.databinding.BaseObservable
import android.databinding.Bindable
import epost.android.mitake.com.fbloginkotlinsample.BR
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties

class TrustTradeInfo: BaseObservable {

    constructor()

    @get:Bindable
    var mainId: String = GlobalProperties.account.userInfo.uuid!!
        set(value) {
            field = value
            notifyPropertyChanged(BR.mainId)
        }

    @get:Bindable
    var otherId: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.otherId)
        }

    @get:Bindable
    var systemTime: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.systemTime)
        }

    @get:Bindable
    var mainScore: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.mainScore)
        }

    @get:Bindable
    var otherScore: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.otherScore)
        }

    @get:Bindable
    var tradeNote: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.tradeNote)
        }


    //狀態: 0:新建交易 1:雙方確認 3:完成
    @get:Bindable
    var orderState: String = "0"
        set(value) {
            field = value
            notifyPropertyChanged(BR.orderState)
        }
}
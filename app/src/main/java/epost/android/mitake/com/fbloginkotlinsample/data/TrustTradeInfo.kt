package epost.android.mitake.com.fbloginkotlinsample.data

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.TextUtils
import epost.android.mitake.com.fbloginkotlinsample.BR
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties

class TrustTradeInfo : BaseObservable {

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

    //交易單系統時間
    @get:Bindable
    var systemTime: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.systemTime)
        }

    //我方下注分
    @get:Bindable
    var mainScore: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.mainScore)
        }


    //對方下注分
    @get:Bindable
    var otherScore: String = "等待對方確認"
        set(value) {
            field = if (TextUtils.isEmpty(value)) "等待對方確認" else value
            notifyPropertyChanged(BR.otherScore)
        }

    @get:Bindable
    var tradeTitle: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.tradeTitle)
        }

    @get:Bindable
    var tradeNote: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.tradeNote)
        }


    //狀態: 0:新建交易 1:雙方確認 3:完成 4:申訴中
    @get:Bindable
    var orderState: String = "0"
        set(value) {
            field = value
            notifyPropertyChanged(BR.orderState)
        }
}
package epost.android.mitake.com.fbloginkotlinsample.data

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.google.firebase.Timestamp
import epost.android.mitake.com.fbloginkotlinsample.BR

class RulingInfo : BaseObservable {

    constructor()

    @get:Bindable
    var title: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    var detail: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.detail)
        }


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

    //正向得票數
    @get:Bindable
    var positiveCount: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.positiveCount)
        }

    @get:Bindable
    var positiveList : ArrayList<String> = ArrayList()
    set(value) {
        field = value
        notifyPropertyChanged(BR.positiveList)
    }

    //負向得票數
    @get:Bindable
    var negativeCount: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.negativeCount)
        }

}
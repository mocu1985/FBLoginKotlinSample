package epost.android.mitake.com.fbloginkotlinsample.data

import android.databinding.BaseObservable
import android.databinding.Bindable
import epost.android.mitake.com.fbloginkotlinsample.BR

class RulingObject : BaseObservable {

    lateinit var rulingInfo: RulingInfo

    constructor()
    constructor(rulingId: String, rulingInfo: RulingInfo) : super() {
        this.rulingId = rulingId
        this.rulingInfo = rulingInfo
    }

    constructor(rulingInfo: RulingInfo) : super() {
        this.rulingInfo = rulingInfo
    }


    @get:Bindable
    var rulingId: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.rulingId)
        }

}
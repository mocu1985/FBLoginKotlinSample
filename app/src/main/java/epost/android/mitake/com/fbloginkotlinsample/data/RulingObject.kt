package epost.android.mitake.com.fbloginkotlinsample.data

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.google.firebase.Timestamp
import epost.android.mitake.com.fbloginkotlinsample.BR

class RulingObject : BaseObservable {

     lateinit var rulingInfo: RulingInfo

     constructor()
    constructor(rulingInfo: RulingInfo) : super() {
        this.rulingInfo = rulingInfo
    }


    @get:Bindable
    var rulingId: String = Timestamp.now().seconds.toString()
        set(value) {
            field = value
            notifyPropertyChanged(BR.rulingId)
        }

}
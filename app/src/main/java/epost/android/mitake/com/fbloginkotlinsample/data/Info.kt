package epost.android.mitake.com.fbloginkotlinsample.data

import android.databinding.BaseObservable
import android.databinding.Bindable
import epost.android.mitake.com.fbloginkotlinsample.BR

public class Info : BaseObservable {

    @get:Bindable
    var name: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var birthday: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.birthday)
        }

    @get:Bindable
    var sex: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.sex)
        }

    @get:Bindable
    var address: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.address)
        }

    constructor()
    constructor(name: String, birthday: String, sex: String = "男", address: String = "") {
        this.name = name
        this.birthday = birthday
        this.sex = sex
        this.address = address
    }
}

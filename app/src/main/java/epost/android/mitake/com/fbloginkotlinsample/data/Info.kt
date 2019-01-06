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

    @get:Bindable
    var trustScore: String? = "50.00"
        set(value) {
            field = value
            notifyPropertyChanged(BR.trustScore)
        }

    @get:Bindable
    var uuid: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.uuid)
        }

    //TODO 增加 完成交易統計、爭議統計

    constructor()
    constructor(name: String, birthday: String, sex: String = "男", address: String = "") {
        this.name = name
        this.birthday = birthday
        this.sex = sex
        this.address = address
    }
}

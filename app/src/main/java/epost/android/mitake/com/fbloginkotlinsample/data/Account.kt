package epost.android.mitake.com.kotlinsample

import android.databinding.BaseObservable
import android.databinding.Bindable
import epost.android.mitake.com.fbloginkotlinsample.BR
import epost.android.mitake.com.fbloginkotlinsample.data.Info

public class Account : BaseObservable {

    //    lateinit var uid: String   //身分證
    @get:Bindable
    var uid: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.uid)
        }

    lateinit var userInfo: Info


    //document.toObject一直失敗，原因是少空建構子
    constructor()

    constructor(name: String = "", uid: String = "", birthday: String = "", sex: String = "男", address: String = "") {
        this.uid = uid
        this.userInfo = Info(name, birthday, sex, address)
    }
}
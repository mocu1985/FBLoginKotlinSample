package epost.android.mitake.com.fbloginkotlinsample.data

import android.databinding.BaseObservable
import android.databinding.Bindable
import epost.android.mitake.com.fbloginkotlinsample.BR

//申訴單舉證物件
class ProofObject: BaseObservable {

    constructor(content: String, imgList: ArrayList<String>) : super() {
        this.content = content
        this.imgList = imgList
    }

    @get:Bindable
    var content: String = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.content)
    }

    @get:Bindable
    var imgList: ArrayList<String> = ArrayList()
    set(value) {
        field = imgList
        notifyPropertyChanged(BR.imgList)
    }
}
package epost.android.mitake.com.fbloginkotlinsample.viewmodel

import android.arch.lifecycle.ViewModel
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties

class RulingDetailViewModel : ViewModel() {

    var rulingObj = GlobalProperties.rulingObj
    var position: Int = 0

    fun getDetailContent(): String{
        return rulingObj.rulingInfo.detailList.get(position).detail
    }

}

package epost.android.mitake.com.item

import epost.android.mitake.com.fbloginkotlinsample.BR
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.data.RulingObject
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentActivity

class RulingItem : BaseItem {

    private lateinit var act: ParentActivity
    var rulingObject: RulingObject

    constructor(rulingObject: RulingObject) {
        this.rulingObject = rulingObject
    }

    constructor(act: ParentActivity, rulingObject: RulingObject) {
        this.act = act
        this.rulingObject = rulingObject
    }


    override fun getLayout(): Int {
        return R.layout.item_ruling
    }

    override fun getVariableId(): Int {
        return BR.item
    }

    fun getVoteResult(): Int {
        var info = rulingObject.rulingInfo
        var total = info.positiveCount + info.negativeCount
        return info.positiveCount * 100 / total
    }

    fun getPositivePercent(): String {
        var info = rulingObject.rulingInfo
        var total = info.positiveCount + info.negativeCount
        return (info.positiveCount * 100 / total).toString() + "%"
    }

    fun getNagativePercent(): String {
        var info = rulingObject.rulingInfo
        var total = info.positiveCount + info.negativeCount
        return (info.negativeCount * 100 / total).toString() + "%"
    }

}
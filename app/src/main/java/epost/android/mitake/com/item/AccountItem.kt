package epost.android.mitake.com.item

import com.baurine.multitypeadapter.MultiTypeAdapter
import epost.android.mitake.com.fbloginkotlinsample.BR
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.data.Info

class AccountItem: MultiTypeAdapter.IItem {

    lateinit var account:Info

    constructor(account: Info) {
        this.account = account
    }


    override fun getLayout(): Int {
        return R.layout.item_account
    }

    override fun getVariableId(): Int {
        return BR.item
    }

}
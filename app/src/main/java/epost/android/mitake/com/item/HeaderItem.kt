package epost.android.mitake.com.item

import com.android.databinding.library.baseAdapters.BR
import com.baurine.multitypeadapter.MultiTypeAdapter
import epost.android.mitake.com.fbloginkotlinsample.R


class HeaderItem : MultiTypeAdapter.IItem {
    override fun getVariableId(): Int {
        return BR.item
    }

    override fun getLayout(): Int {
        return R.layout.item_header
    }

}
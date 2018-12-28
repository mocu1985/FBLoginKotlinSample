package epost.android.mitake.com.item

import com.android.databinding.library.baseAdapters.BR
import com.baurine.multitypeadapter.MultiTypeAdapter
import epost.android.mitake.com.fbloginkotlinsample.R


class FooterItem : MultiTypeAdapter.IItem {
    override fun getLayout(): Int {
        return R.layout.item_footer
    }

    override fun getVariableId(): Int {
        return BR.item
    }

    private var state = LOADING

    val isLoading: Boolean
        get() = state == LOADING

    val isError: Boolean
        get() = state == ERROR

    val isNoMore: Boolean
        get() = state == NO_MORE

    ///////////////////////////////////////////////
    fun setState(state: Int): FooterItem {
        this.state = state
        return this
    }

    companion object {

        ///////////////////////////////////////////////
        // viewData part
        // FooterItem has 3 states: Loading, Error, NoMore
        val LOADING = 0
        val ERROR = 1
        val NO_MORE = 2
    }
}

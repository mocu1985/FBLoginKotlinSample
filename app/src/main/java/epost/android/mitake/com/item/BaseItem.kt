package epost.android.mitake.com.item

import android.view.View
import com.baurine.multitypeadapter.MultiTypeAdapter

abstract class BaseItem : MultiTypeAdapter.IItem {

    var onClickListener: View.OnClickListener? = null

}
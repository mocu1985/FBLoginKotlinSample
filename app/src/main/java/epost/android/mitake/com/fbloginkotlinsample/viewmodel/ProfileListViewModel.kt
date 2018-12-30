package epost.android.mitake.com.fbloginkotlinsample.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.baurine.multitypeadapter.MultiTypeAdapter
import com.google.firebase.firestore.FirebaseFirestore
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.data.TrustTradeInfo
import epost.android.mitake.com.fbloginkotlinsample.data.TrustTradeObject
import epost.android.mitake.com.fbloginkotlinsample.databinding.ProfileListFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.util.JDialog
import epost.android.mitake.com.item.FooterItem
import epost.android.mitake.com.item.HeaderItem
import epost.android.mitake.com.item.TrustTradeItem


class ProfileListViewModel : ViewModel() {

    private val multiAdapter: MultiTypeAdapter = MultiTypeAdapter()
    private lateinit var headerItem: HeaderItem
    private lateinit var footerItem: FooterItem
    lateinit var binding: ProfileListFragmentBinding

    var trustTradeList = HashMap<String?, TrustTradeObject>()

    fun refreshData() {

    }

    fun retrieveItems() {
    }

    private fun addDataItems(count: Int) {

    }


    fun loadAccountsData(context: Context) {
        if (multiAdapter.items.size == 0) {
            var mDataRef = FirebaseFirestore.getInstance()
            mDataRef.collection(GlobalProperties.TRUST_TRADE_ROOT)
                .whereEqualTo("mainId", GlobalProperties.account.userInfo.uuid)
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        it.getResult()!!.forEach {
                            Log.d("*****", it.id + " : " + it.getData())

                            var trustOjb = it.toObject(TrustTradeInfo::class.java)
                            var tObj = TrustTradeObject(it.id, trustOjb)
                            trustTradeList.set(it.id, tObj)

                        }

                        trustTradeList.forEach {
                            var trustItem = TrustTradeItem(it.key!!, it.value)
                            multiAdapter.addItem(trustItem)
                        }

                        binding.recyView.adapter.notifyDataSetChanged()

                    } else {
                        JDialog.showDialog(context!!, context.getString(R.string.alt_hint), "查無資料")
                        multiAdapter.addItem(FooterItem().setState(FooterItem.ERROR))
                    }
                }
        }
    }


    fun initView(context: Context) {
        binding.swpLayout.setOnRefreshListener { refreshData() }

        binding.recyView.setHasFixedSize(true)
        val llm = LinearLayoutManager(context)
        binding.recyView.layoutManager = llm
        binding.recyView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && llm.findLastVisibleItemPosition() >= multiAdapter.getItemCount() - 1) {
                    loadMoreData()
                }
            }
        })

        binding.recyView.adapter = multiAdapter
    }


    private fun loadMoreData() {
    }

}

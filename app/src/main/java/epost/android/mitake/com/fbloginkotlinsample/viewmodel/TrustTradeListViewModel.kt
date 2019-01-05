package epost.android.mitake.com.fbloginkotlinsample.viewmodel

import android.arch.lifecycle.ViewModel
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.baurine.multitypeadapter.MultiTypeAdapter
import com.google.firebase.firestore.FirebaseFirestore
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.data.TrustTradeInfo
import epost.android.mitake.com.fbloginkotlinsample.data.TrustTradeObject
import epost.android.mitake.com.fbloginkotlinsample.databinding.TrustTradeListFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentActivity
import epost.android.mitake.com.fbloginkotlinsample.util.JDialog
import epost.android.mitake.com.item.FooterItem
import epost.android.mitake.com.item.HeaderItem
import epost.android.mitake.com.item.TrustTradeItem
import mma.security.component.diagnostics.Debuk


//class TrustTradeListViewModel : ViewModel(), ParentActivity.ActivityCallBack {
class TrustTradeListViewModel : ViewModel() {
    val multiAdapter: MultiTypeAdapter = MultiTypeAdapter()
    private lateinit var headerItem: HeaderItem
    private lateinit var footerItem: FooterItem
    lateinit var binding: TrustTradeListFragmentBinding
    lateinit var act: ParentActivity

    var trustTradeList = HashMap<String?, TrustTradeObject>()

    fun refreshData() {
        loadAccountsData(true)
    }

    fun retrieveItems() {
    }

    private fun addDataItems(count: Int) {

    }

    fun isEmpty(): Boolean {
        return multiAdapter.items.isEmpty()
    }


    fun loadAccountsData(isRefresh: Boolean = false) {
        if (multiAdapter.items.size == 0 || isRefresh || GlobalProperties.doRefresh) {
            var mDataRef = FirebaseFirestore.getInstance()
            mDataRef.collection(GlobalProperties.TRUST_TRADE_ROOT)
                .document(GlobalProperties.currect_id)
                .collection(GlobalProperties.TRADING)
                .whereEqualTo("mainId", GlobalProperties.account.userInfo.uuid)
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        trustTradeList.clear()
                        multiAdapter.clearItems()

                        it.getResult()!!.forEach {
                            Debuk.WriteLine("*****", it.id + " : " + it.getData())

                            var trustOjb = it.toObject(TrustTradeInfo::class.java)
                            var tObj = TrustTradeObject(it.id, trustOjb)
                            trustTradeList.set(it.id, tObj)

                        }

                        trustTradeList.forEach {
                            var trustItem = TrustTradeItem(act, it.key!!, it.value, this)
                            multiAdapter.addItem(trustItem)
                        }

//                        if (multiAdapter.items.isEmpty()) {
//                            binding.tvMsg.visibility = View.VISIBLE
//                        }
                        binding.recyView.adapter.notifyDataSetChanged()

                    } else {
                        JDialog.showDialog(act!!, act.getString(R.string.alt_hint), "查無資料")
                        multiAdapter.addItem(FooterItem().setState(FooterItem.ERROR))
                    }

                    binding.swpLayout.isRefreshing = false
                    GlobalProperties.doRefresh = false
                }
        }
    }


    fun initView() {
        binding.swpLayout.setColorSchemeResources(
            android.R.color.holo_red_light,
            android.R.color.holo_blue_light,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light
        )

        binding.swpLayout.setOnRefreshListener { refreshData() }

        binding.recyView.setHasFixedSize(true)
        val llm = LinearLayoutManager(act)
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

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        when (requestCode) {
//            GlobalProperties.TRADE_ORDER_DELETE ->
//                if(resultCode == Activity.RESULT_OK) loadAccountsData(true)
//        }
//    }

}

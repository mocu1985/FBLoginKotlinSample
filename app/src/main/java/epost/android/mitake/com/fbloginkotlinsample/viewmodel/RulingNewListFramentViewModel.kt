package epost.android.mitake.com.fbloginkotlinsample.viewmodel

import android.arch.lifecycle.ViewModel
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.baurine.multitypeadapter.MultiTypeAdapter
import com.google.firebase.firestore.FirebaseFirestore
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.data.RulingInfo
import epost.android.mitake.com.fbloginkotlinsample.data.RulingObject
import epost.android.mitake.com.fbloginkotlinsample.databinding.RulingNewListFramentFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentActivity
import epost.android.mitake.com.fbloginkotlinsample.util.JDialog
import epost.android.mitake.com.item.FooterItem
import epost.android.mitake.com.item.RulingItem
import mma.security.component.diagnostics.Debuk

class RulingNewListFramentViewModel : ViewModel() {

    lateinit var binding: RulingNewListFramentFragmentBinding
    val multiAdapter: MultiTypeAdapter = MultiTypeAdapter()
    lateinit var act: ParentActivity
    var rulingList = HashMap<String?, RulingObject>()


    fun refreshData() {
        loadRulingsData(true)
    }

    fun retrieveItems() {
    }

    private fun addDataItems(count: Int) {

    }

    fun isEmpty(): Boolean {
        return multiAdapter.items.isEmpty()
    }


    fun loadRulingsData(isRefresh: Boolean = false) {
        if (multiAdapter.items.size == 0 || isRefresh || GlobalProperties.doRefresh) {
            var mDataRef = FirebaseFirestore.getInstance()
            mDataRef.collection(GlobalProperties.RULING_ROOT)
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        rulingList.clear()
                        multiAdapter.clearItems()
                        it.result!!.forEach {
                            Debuk.WriteLine("*****", it.id + " : " + it.getData())

                            var rulingInfo = it.toObject(RulingInfo::class.java)
//                            var tObj = RulingObject(it.id, rulingInfo)
                            var rObj = RulingObject(rulingInfo)

                            rulingList.set(it.id, rObj)
                        }

                        rulingList.forEach {
                            var rulingItem = RulingItem(it.value)
//                            var rulingItem = RulingItem(act, it.value)
                            multiAdapter.addItem(rulingItem)
                        }

                        binding.recyView.adapter.notifyDataSetChanged()
                    } else {
                        JDialog.showDialog(act!!, act.getString(R.string.alt_hint), "查無資料")
                        multiAdapter.addItem(FooterItem().setState(FooterItem.ERROR))
                    }

                    binding.swpLayout.isRefreshing = false
                    GlobalProperties.doRefresh = false
                }
        } else {
            binding.recyView.adapter.notifyDataSetChanged()
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
}

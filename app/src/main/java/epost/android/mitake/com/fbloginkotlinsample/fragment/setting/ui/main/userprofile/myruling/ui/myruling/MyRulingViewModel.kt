package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.userprofile.myruling.ui.myruling

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.baurine.multitypeadapter.MultiTypeAdapter
import com.google.firebase.firestore.FirebaseFirestore
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.data.RulingInfo
import epost.android.mitake.com.fbloginkotlinsample.data.RulingObject
import epost.android.mitake.com.fbloginkotlinsample.databinding.MyRulingFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.util.JDialog
import epost.android.mitake.com.item.FooterItem
import epost.android.mitake.com.item.RulingItem
import mma.security.component.diagnostics.Debuk

class MyRulingViewModel : ViewModel() {

    lateinit var act: FragmentActivity
    lateinit var binding: MyRulingFragmentBinding
    val multiAdapter: MultiTypeAdapter = MultiTypeAdapter()
    var rulingList = HashMap<String?, RulingObject>()
    var isEmpty: ObservableBoolean = ObservableBoolean(true)


    fun initView() {
        binding.swpLayout.setColorSchemeResources(
            android.R.color.holo_red_light,
            android.R.color.holo_blue_light,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light
        )

        binding.swpLayout.setOnRefreshListener { refreshData() }

        binding.recycleView.setHasFixedSize(true)
        val llm = LinearLayoutManager(act)
        binding.recycleView.layoutManager = llm
        binding.recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && llm.findLastVisibleItemPosition() >= multiAdapter.getItemCount() - 1) {
                    loadMoreData()
                }
            }
        })

        binding.recycleView.adapter = multiAdapter
    }


    private fun loadMoreData() {
    }

    fun refreshData() {
        loadRulingsData(true)
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
                            Debuk.WriteLine("forEach", it.id + " : " + it.getData())

                            var rulingInfo = it.toObject(RulingInfo::class.java)
//                            var tObj = RulingObject(it.id, rulingInfo)
                            var rObj = RulingObject(rulingInfo)

                            rulingList.set(it.id, rObj)
                        }

                        rulingList.forEach {
                            var rulingItem = RulingItem(act, it.value)
//                            var rulingItem = RulingItem(act, it.value)
                            multiAdapter.addItem(rulingItem)
                            isEmpty.set(false)
                        }

                        binding.recycleView.adapter.notifyDataSetChanged()
                    } else {
                        JDialog.showDialog(act!!, act.getString(R.string.alt_hint), "查無資料")
                        multiAdapter.addItem(FooterItem().setState(FooterItem.ERROR))
                        isEmpty.set(true)
                    }

                    binding.swpLayout.isRefreshing = false
                    GlobalProperties.doRefresh = false
                }
        } else {
            binding.recycleView.adapter.notifyDataSetChanged()
        }
    }


    fun initData() {

    }
}

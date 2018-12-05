package epost.android.mitake.com.fbloginkotlinsample.fragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baurine.multitypeadapter.MultiTypeAdapter
import com.google.firebase.database.*
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.data.Info
import epost.android.mitake.com.fbloginkotlinsample.databinding.ProfileListFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentFragment
import epost.android.mitake.com.fbloginkotlinsample.viewmodel.ProfileListViewModel
import epost.android.mitake.com.item.AccountItem
import epost.android.mitake.com.item.FooterItem
import epost.android.mitake.com.item.HeaderItem




class ProfileListFragment : ParentFragment() {
    companion object {
        fun newInstance() = ProfileListFragment()
    }

    private lateinit var swipLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ProfileListViewModel
    private val multiAdapter: MultiTypeAdapter = MultiTypeAdapter()
    private lateinit var headerItem: HeaderItem
    private lateinit var footerItem: FooterItem
    private lateinit var infoItemList: ArrayList<AccountItem>
    private lateinit var binding: ProfileListFragmentBinding

    var datebase: FirebaseDatabase? = null
    var mDataRef: DatabaseReference? = null
    var accountList = HashMap<String?, Info?>()

    override fun initData() {
        initItem()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_list_fragment, container, false)

        swipLayout = binding.swpLayout
        recyclerView = binding.recyView

        viewModel = ViewModelProviders.of(this).get(ProfileListViewModel::class.java)

        initView()

        return binding.root
    }

    private fun initItem() {
        //讀取資料庫帳號
        loadAccountsData()

    }

    private fun loadAccountsData() {
        infoItemList = ArrayList()


        datebase = FirebaseDatabase.getInstance()
        mDataRef = datebase?.getReference("accounts")

        mDataRef!!.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    p0.children.forEach {
                        var infoData = it.child("userInfo").getValue(Info::class.java)
                        accountList.set(it.key, infoData)
                    }

                    accountList.forEach {
                        var infoItem = AccountItem(it.value!!)
                        multiAdapter.addItem(infoItem)
                    }

                    recyclerView.adapter.notifyDataSetChanged()

                }
            }
        })
    }

    private fun initView() {
        swipLayout.setOnRefreshListener { refreshData() }

        recyclerView.setHasFixedSize(true)
        val llm = LinearLayoutManager(context)
        recyclerView.layoutManager = llm
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && llm.findLastVisibleItemPosition() >= multiAdapter.getItemCount() - 1) {
                    loadMoreData()
                }
            }
        })
        recyclerView.adapter = multiAdapter
    }

    private fun loadMoreData() {
    }

    private fun refreshData() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}

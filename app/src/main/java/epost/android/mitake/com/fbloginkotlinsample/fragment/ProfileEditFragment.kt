package epost.android.mitake.com.fbloginkotlinsample.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.kotlinsample.Account
import kotlinx.android.synthetic.main.fragment_profile_edit.view.*
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProfileEditFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProfileEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProfileEditFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    var datebase: FirebaseDatabase? = null
    var mDataRef: DatabaseReference? = null
    lateinit var currView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        initDatabase()
    }

    private fun initDatabase() {
        datebase = FirebaseDatabase.getInstance()
        mDataRef = datebase?.getReference("accounts/" + Account.id + "/userInfo")


//        mDataRef?.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                Log.d("****", p0.getValue().toString())
//                Account.account.userInfo = p0.getValue(Info::class.java)!!
//                Log.d("****", Account.account.userInfo.name)
//            }
//
//        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        currView = inflater.inflate(R.layout.fragment_profile_edit, container, false)

        currView.tv_id.text = Account.id
        currView.edt_name.setText(Account.account.userInfo.name)
        currView.edt_bir.setText(Account.account.userInfo.birthday)
        currView.edt_address.setText(Account.account.userInfo.address)

        setViewFocus(false)

        currView.btn_confirm.setOnClickListener {
            if (currView.btn_confirm.text.equals("編輯")) {
                setViewFocus(true)
            } else {
                doUpdate()
            }
        }

        return currView
    }

    private fun setViewFocus(state: Boolean) {
        currView.edt_name.isFocusable = state
        currView.edt_name.isFocusableInTouchMode = state
        currView.edt_bir.isFocusable = state
        currView.edt_bir.isFocusableInTouchMode = state
        currView.edt_address.isFocusable = state
        currView.edt_address.isFocusableInTouchMode = state

        currView.btn_confirm.text = if (state) "確定" else "編輯"
    }


    private fun doUpdate() {
        setViewFocus(false)

        Account.account.userInfo.name = currView.edt_name.text.toString()
        Account.account.userInfo.birthday = currView.edt_bir.text.toString()
        Account.account.userInfo.address = currView.edt_address.text.toString()

//        mDataRef?.setValue(Account.account.userInfo,
//            object : DatabaseReference.CompletionListener {
//            override fun onComplete(p0: DatabaseError?, p1: DatabaseReference) {
//                if (p0 == null) Toast.makeText(context, "成功", Toast.LENGTH_SHORT).show()
//            }
//        })

        mDataRef = datebase?.getReference("accounts")

        var map = HashMap<String, Account>()
        map.put(Account.id, Account.account)

        mDataRef?.updateChildren(
            map as Map<String, Any>,
            object : DatabaseReference.CompletionListener {
                override fun onComplete(p0: DatabaseError?, p1: DatabaseReference) {
                    if (p0 == null) Toast.makeText(context, "成功", Toast.LENGTH_SHORT).show()
                }
            })
    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileEditFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileEditFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

package epost.android.mitake.com.fbloginkotlinsample.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.*
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.databinding.FragmentProfileEditBinding
import epost.android.mitake.com.fbloginkotlinsample.viewmodel.ProfileEditViewModel
import epost.android.mitake.com.kotlinsample.Account
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

    lateinit var viewModel: ProfileEditViewModel
    lateinit var binding: FragmentProfileEditBinding

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
        mDataRef = datebase?.getReference("accounts/" + Account.id!!.get() + "/userInfo")


        mDataRef?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                viewModel.updateInfo(p0, binding)

            }

        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_edit, container, false)

        viewModel = ViewModelProviders.of(this).get(ProfileEditViewModel::class.java)

        viewModel.info = Account.account.userInfo

        binding.btnConfirm.setOnClickListener {
            if(viewModel.isEdit.get()!!){
                doUpdate()
            }
            viewModel.setBtnText()
        }

        binding.model = viewModel

        return binding.root
    }


    private fun doUpdate() {
        Account.account.userInfo.name = binding.edtName.text.toString()
        Account.account.userInfo.birthday = binding.edtBir.text.toString()
        Account.account.userInfo.address = binding.edtAddress.text.toString()

        mDataRef = datebase?.getReference("accounts")

        var map = HashMap<String, Account>()
        map.put(Account.id!!.get()!!, Account.account)

        mDataRef?.updateChildren(
            map as Map<String, Any>,
            object : DatabaseReference.CompletionListener {
                override fun onComplete(p0: DatabaseError?, p1: DatabaseReference) {
                    if (p0 == null) {
                        Toast.makeText(context, "成功", Toast.LENGTH_SHORT).show()
                    }
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

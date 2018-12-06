package epost.android.mitake.com.fbloginkotlinsample.application

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore

class MyApplication: Application() {

    val rootRef = FirebaseFirestore.getInstance()

    override fun onCreate() {
        super.onCreate()
    }
}
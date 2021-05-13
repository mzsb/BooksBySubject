package hu.mzsb.booksbysubject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import hu.mzsb.booksbysubject.util.network.isInternetAvailable

class MainActivity : AppCompatActivity() {
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        if(isInternetAvailable){
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.CONTENT, "login")
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
        }

        setContentView(R.layout.activity_main)
    }
}
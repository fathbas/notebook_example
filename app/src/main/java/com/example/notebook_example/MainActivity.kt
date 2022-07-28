package com.example.notebook_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.notebook_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

/*
        firebaseAnalytics = Firebase.analytics
        logCustomEvent()
        if (BuildConfig.DEBUG) {
            FirebaseCrashlytics.getInstance()
                .setCrashlyticsCollectionEnabled(false)
        }
        val crashButton = Button(this)
        crashButton.text = getString(R.string.crash_button_name)
        crashButton.setOnClickListener {

            throw RuntimeException("Test Crash") // Force a crash

        }

        addContentView(
            crashButton, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
 */


    }
/*
    private fun logCustomEvent() {
        val name = "customImage"
        val text = "I'd love to hear more about $name"


        firebaseAnalytics.logEvent("share_image") {
            param("image_name", name)
            param("full_text", text)
        }
    }

    private fun setupToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            // Get new FCM registration token
            //val token = task.result
        })
    }
 */
}
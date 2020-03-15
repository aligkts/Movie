package com.aligkts.movie.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aligkts.movie.common.RxAwareViewModel
import com.google.firebase.remoteconfig.BuildConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Ali Göktaş on 15,March,2020
 */
class SplashViewModel @Inject constructor() : RxAwareViewModel() {

    private val TAG = "SplashViewModel"
    private var APP_SPLASH_TEXT = "app_splash_text"

    private val splashTextLiveData = MutableLiveData<String>()
    fun getSplashTextLiveData(): LiveData<String> = splashTextLiveData

    private val splashNavigationLiveData = MutableLiveData<SplashState>()
    fun getNavigationLiveData(): LiveData<SplashState> = splashNavigationLiveData

    private val remoteConfig = FirebaseRemoteConfig.getInstance()
    private val configBuilder = FirebaseRemoteConfigSettings.Builder()

    init {
        if (BuildConfig.DEBUG)
            configBuilder.minimumFetchIntervalInSeconds = 0L
        remoteConfig.setConfigSettingsAsync(configBuilder.build())
        val splashText = remoteConfig.getString(APP_SPLASH_TEXT)
        splashTextLiveData.value = splashText
        updateRemoteConfigValues()
    }

    fun startTimer() {
        GlobalScope.launch {
            delay(3000)
            splashNavigationLiveData.postValue(SplashState.MainActivity())
        }
    }

    private fun updateRemoteConfigValues() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d(TAG, "Config params updated: $updated");
                }
            }
    }

    sealed class SplashState {
        class MainActivity : SplashState()
    }

}


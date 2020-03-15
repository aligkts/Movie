package com.aligkts.movie.ui.splash

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aligkts.movie.MainActivity
import com.aligkts.movie.R
import com.aligkts.movie.databinding.ActivitySplashBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by Ali Göktaş on 15,March,2020
 */
class SplashActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivitySplashBinding
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        splashViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(SplashViewModel::class.java)

        splashViewModel.getNavigationLiveData().observe(this, Observer {
            when (it) {
                is SplashViewModel.SplashState.MainActivity -> {
                    goToMainActivity()
                }
            }
        })

        splashViewModel.getSplashTextLiveData().observe(this, Observer {
            binding.txtSplash.text = it
        })
        binding.txtSplash.setOnClickListener { splashViewModel.startTimer() }
        binding.imgSplashIcon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide))
    }

    private fun goToMainActivity() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }
}
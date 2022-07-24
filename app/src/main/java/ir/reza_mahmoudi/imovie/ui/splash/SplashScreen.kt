package ir.reza_mahmoudi.imovie.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ir.reza_mahmoudi.imovie.databinding.ActivitySplashScreenBinding
import ir.reza_mahmoudi.imovie.ui.home.HomeActivity

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        normalLaunch()
    }
    private fun normalLaunch() {
        binding.loadingAnimation.setAnimation("loading_animation.json")
        binding.loadingAnimation.playAnimation()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreen, HomeActivity::class.java))
            finish()
        }, 3)
    }
}
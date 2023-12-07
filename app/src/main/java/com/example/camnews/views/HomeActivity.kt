package com.example.camnews.views

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.camnews.R
import com.example.camnews.adapters.CategoryAdapter
import com.example.camnews.databinding.ActivityHomeBinding
import com.example.camnews.viewmodels.NewsViewModel
import java.util.Calendar


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val newsViewModel = NewsViewModel()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.newsViewModel = newsViewModel

        binding.categoryList.adapter = CategoryAdapter(this, newsViewModel)

        initGreeting()
        binding.lifecycleOwner = this
    }

    @SuppressLint("SetTextI18n")
    private fun initGreeting(testHour: Int? = null) {
        val current = Calendar.getInstance()
        val hour = testHour ?: current.get(Calendar.HOUR_OF_DAY)
        Log.d("Hour", hour.toString())

        val greetingText = binding.greetingText
        val timeLogo = binding.timeLogo

        when (hour) {
            in 6..9 -> {
                greetingText.text = "Good Morning!"
                if (hour == 6) {
                    timeLogo.setImageResource(R.drawable.sunrise)
                } else {
                    timeLogo.setImageResource(R.drawable.day)
                }
            }
            in 10..11 -> {
                greetingText.text = "Hello, Guys!"
                timeLogo.setImageResource(R.drawable.day)
            }
            in 12..15 -> {
                greetingText.text = "Good Afternoon!"
                timeLogo.setImageResource(R.drawable.day)
            }
            in 16..22 -> {
                greetingText.text = "Good Evening!"
                if (hour <= 17) {
                    timeLogo.setImageResource(R.drawable.sunset)
                } else if (hour <= 18) {
                    timeLogo.setImageResource(R.drawable.day_to_night)
                } else {
                    timeLogo.setImageResource(R.drawable.night)
                }
            }
            in 23..24 -> {
                greetingText.text = "Happy Midnight!"
                timeLogo.setImageResource(R.drawable.night)
            }
            else -> {
                greetingText.text = "Happy New Day!"
                timeLogo.setImageResource(R.drawable.night)
            }
        }
    }
}
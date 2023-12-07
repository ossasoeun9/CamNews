package com.example.camnews.views

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.camnews.R
import com.example.camnews.databinding.ActivityDetailArticleBinding
import com.example.camnews.models.data.Article

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding
    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_article)

        binding.detailArticleToolbar.setNavigationOnClickListener {
            finish()
        }
        article  = intent.getSerializableExtra("article", Article::class.java)!!
        updateUI()
    }

    private fun updateUI() {
        try {
            binding.webView.loadUrl(article.url)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
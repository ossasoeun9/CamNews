package com.example.camnews.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.camnews.R
import com.example.camnews.models.data.Article
import com.example.camnews.utils.Utils

class ArticleAdapter(
    private val articles: List<Article>
) : RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {
    class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView
        val descriptionView: TextView
        val authorAndDateView: TextView
        val imageView: ImageView

        init {
            titleView = itemView.findViewById(R.id.articleTitle)
            descriptionView = itemView.findViewById(R.id.articleDescription)
            authorAndDateView = itemView.findViewById(R.id.articleAuthorAndDate)
            imageView = itemView.findViewById(R.id.articleImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.article_layout, parent, false)
        return ArticleHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val article = articles[position]
        holder.titleView.text = article.title
        holder.descriptionView.text = article.description

        val dateAsString = Utils.stringToDateFormatted(article.publishedAt)

        holder.authorAndDateView.text = "${article.author ?: "Unknown"} - $dateAsString"

        Glide.with(holder.imageView)
            .load(article.urlToImage)
            .error(R.drawable.no_image)
            .into(holder.imageView)
    }
}
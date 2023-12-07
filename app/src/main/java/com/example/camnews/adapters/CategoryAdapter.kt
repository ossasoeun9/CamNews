package com.example.camnews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.camnews.R
import com.example.camnews.databinding.CategoryLayoutBinding
import com.example.camnews.viewmodels.NewsViewModel

class CategoryAdapter(private val lifecycleOwner: LifecycleOwner,private val newsViewModel: NewsViewModel) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    private val categories = listOf(
        "All",
        "Sports",
        "Health",
        "Technology",
        "Business",
        "Entertainment",
        "Science",
    )

    class CategoryHolder(private val _binding: CategoryLayoutBinding) : RecyclerView.ViewHolder(_binding.root) {
        val binding : CategoryLayoutBinding get() = _binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = DataBindingUtil.inflate<CategoryLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.category_layout,
            parent,
            false
        )
        return CategoryHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val cat = categories[position]
        holder.binding.item = cat
        holder.binding.newsViewModel = newsViewModel
        holder.binding.lifecycleOwner = lifecycleOwner
    }
}
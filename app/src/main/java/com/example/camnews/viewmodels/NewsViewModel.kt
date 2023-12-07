package com.example.camnews.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.camnews.adapters.ArticleAdapter
import com.example.camnews.apis.ArticleApis
import com.example.camnews.helpers.HttpClient
import com.example.camnews.helpers.StateEnum
import com.example.camnews.models.data.ArticlesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel() : ViewModel() {
    private val retrofit = HttpClient.getInstance()
    private val articleApis = retrofit.create(ArticleApis::class.java)

    private val _selectedCategory = MutableLiveData<String>("All")
    val selectedCategory: LiveData<String> get() = _selectedCategory

    fun selectCategory(category: String) {
        _selectedCategory.value = category
        callGetArticles()
    }

    private val _state = MutableLiveData<StateEnum>(StateEnum.INITIAL)
    val state: LiveData<StateEnum> get() = _state
    private fun setState(newState: StateEnum) {
        _state.value = newState
    }

    private val _error = MutableLiveData<String>("")
    val error: LiveData<String> get() = _error
    fun setError(newError: String) {
        _error.value = newError
    }

    private val _articles = MutableLiveData<ArticleAdapter>()
    val article: LiveData<ArticleAdapter> get() = _articles

    fun callGetArticles() {
        if (selectedCategory.value == null || selectedCategory.value == "All") {
            getArticles(articleApis.getEverything())
        } else {
            getArticles(articleApis.getTopHeadlines(selectedCategory.value ?: "All", "us"))
        }
    }

    private fun getArticles(call: Call<ArticlesResponse>) {
        setState(StateEnum.LOADING);
        try {
            call.enqueue(object : Callback<ArticlesResponse> {
                @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    if (response.isSuccessful) {
                        _articles.value = ArticleAdapter(response.body()?.articles ?: listOf())
                        setState(StateEnum.LOADED)
                    } else {
                        setError("Something when wrong")
                        setState(StateEnum.ERROR)
                    }
                }

                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                    setError(t.message ?: "Something when wrong")
                    setState(StateEnum.ERROR)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
            setError(e.message ?: "Something when wrong")
            setState(StateEnum.ERROR)
        }
    }

    init {
        callGetArticles()
    }
}
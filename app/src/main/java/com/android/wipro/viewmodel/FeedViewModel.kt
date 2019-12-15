package com.android.wipro.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.wipro.model.Row
import com.android.wipro.network.GithubService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {
    private val service = GithubService()
    private var subscription = CompositeDisposable()
    private var loadingVisibility = MutableLiveData<Int>()
    private var errorLayout = MutableLiveData<Int>()
    var githubData = MutableLiveData<List<Row>>()
    var title = MutableLiveData<String?>()

    val feedData: LiveData<List<Row>>
        get() = githubData
    val getTitle: LiveData<String?>
        get() = title

    val loaderVisbility: LiveData<Int>
        get() = loadingVisibility

    override fun onCleared() {
        super.onCleared()
        if (!subscription.isDisposed) subscription.dispose()
    }

    fun loadGithubData(url: String) {
        loadingVisibility.value = View.VISIBLE
        errorLayout.value = View.GONE
        subscription.add(service.getFeedData(url).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                title.value = it.title
                loadingVisibility.value = View.GONE
                githubData.value = it.rows
            }, {
                title.value = "Error"
                errorLayout.value = View.VISIBLE
                loadingVisibility.value = View.GONE
            })
        )
    }

    val errorLayoutVisibility: LiveData<Int>
        get() = errorLayout
}
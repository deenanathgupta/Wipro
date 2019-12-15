package com.android.wipro.network

import com.android.wipro.model.ResponseFeed
import io.reactivex.Observable

val repository = createGithubService()

class GithubService : FeedResponseCallback {
    override fun getFeedData(url: String): Observable<ResponseFeed> {
        return repository.FeedData(url)
    }
}
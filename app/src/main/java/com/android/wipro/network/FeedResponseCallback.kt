package com.android.wipro.network

import com.android.wipro.model.ResponseFeed
import io.reactivex.Observable

interface FeedResponseCallback {
    fun getFeedData(url:String): Observable<ResponseFeed>
}
package com.example.catchman.studlife.network;

import com.example.catchman.studlife.models.ContactResp;
import com.example.catchman.studlife.models.NewsResp;
import com.example.catchman.studlife.models.PosterResp;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by catchman on 6/14/18.
 */

public interface WebService {

    @GET("/api/contacts.php")
    Observable<List<ContactResp>> getContacts();

    @GET("/api/archive.php")
    Observable<List<NewsResp>> getNews();

    @GET("/api/images.php")
    Observable<List<PosterResp>> getPoster();
}

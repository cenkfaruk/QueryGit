package com.thecavga.querygit.api

import com.thecavga.querygit.ui.repo.model.Repo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {


    @GET("users/{user}/repos")
    fun getUserRepo(
        @Path("user") user: String
        ) : Observable<List<Repo>>
}
package com.thecavga.querygit.ui.repo.presenter

import android.annotation.SuppressLint
import android.content.Context
import com.thecavga.querygit.BuildConfig
import com.thecavga.querygit.api.Service
import com.thecavga.querygit.base.BasePresenter
import com.thecavga.querygit.network.Network
import com.thecavga.querygit.ui.repo.contract.RepoContract
import com.thecavga.querygit.ui.repo.model.Repo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers
import io.reactivex.observers.DisposableObserver



@Suppress("UNCHECKED_CAST")
class RepoPresenter constructor(private val context: Context?):  BasePresenter<RepoContract.View>(),
RepoContract.Presenter {

    @SuppressLint("CheckResult")
    override fun fetchRepos(userName: String) {
        getView()?.showProgress()
        getObservable(userName).subscribeWith(getObserver())
    }


    private fun getObservable(userName: String): Observable<List<Repo>> {
        return Network.getRetrofit()
            .create(Service::class.java)
            .getUserRepo(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


   private fun getObserver(): DisposableObserver<List<Repo>> {
        return object : DisposableObserver<List<Repo>>() {

            override fun onNext(@NonNull repoList: List<Repo>) {
                getView()?.hideProgress()
                getView()?.loadList(repoList)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                getView()?.showError("Error fetching:$e")
            }

            override fun onComplete() {
                //nothing to do
            }
        }
    }
}
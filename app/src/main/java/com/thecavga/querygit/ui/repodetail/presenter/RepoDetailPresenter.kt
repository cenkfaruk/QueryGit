package com.thecavga.querygit.ui.repodetail.presenter

import android.content.Context
import com.thecavga.querygit.base.BasePresenter
import com.thecavga.querygit.ui.repodetail.contract.RepoDetailContract

class RepoDetailPresenter constructor(private val context: Context?):  BasePresenter<RepoDetailContract.View>(),
    RepoDetailContract.Presenter {



}

package com.thecavga.querygit.ui.repo.contract


import com.thecavga.querygit.base.BaseView
import com.thecavga.querygit.ui.repo.model.Repo


interface RepoContract {

     interface View: BaseView {

        fun showProgress()
        fun hideProgress()
        fun showError(errorMsg: String)
        fun loadList(repoList: List<Repo>)

    }

     interface Presenter {
        fun start()
        fun fetchRepos(userName: String)
    }
}
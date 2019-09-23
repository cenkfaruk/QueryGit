package com.thecavga.querygit.ui.repo

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.thecavga.querygit.R
import com.thecavga.querygit.base.BaseActivity
import com.thecavga.querygit.ui.repo.adapter.RepoAdapter
import com.thecavga.querygit.ui.repo.contract.RepoContract
import com.thecavga.querygit.ui.repo.model.Repo
import com.thecavga.querygit.ui.repo.presenter.RepoPresenter
import com.thecavga.querygit.ui.repodetail.RepoDetailActivity
import kotlinx.android.synthetic.main.activity_repo.*
import kotlinx.android.synthetic.main.toolbar.view.*

class RepoActivity : BaseActivity(),  RepoContract.View, View.OnClickListener, RepoAdapter.ItemClickListener {


    private var repoAdapter = RepoAdapter(true)
    override val layoutId: Int = R.layout.activity_repo

    var repoPresenter = RepoPresenter(this)



    override fun initializeDagger() {
       //Object may be provided by Dagger structure
    }

    override fun initializePresenter() {
        repoPresenter.setView(this)
        super.presenter = repoPresenter
        btnSubmitUserName.setOnClickListener(this)
        initToolbar()
    }

    private fun initToolbar(){
        toolbarLayout.tvToolbarTitle.text = getString(R.string.home)
        toolbarLayout.imgToolbarBack.visibility = View.INVISIBLE
    }


    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showError(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }

    override fun loadList(repoList: List<Repo>) {
        repoAdapter.setRepos(repoList)
        repoAdapter.setItemClickListener(this)
        rcyRepoList.adapter = repoAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rcyRepoList.layoutManager = linearLayoutManager


    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnSubmitUserName -> repoPresenter.fetchRepos(edtRepoInput.text.toString())
        }
    }

    override fun onItemClick(repo: Repo, position: Int) {
            startActivity(RepoDetailActivity.intent(this, repo))
    }

}

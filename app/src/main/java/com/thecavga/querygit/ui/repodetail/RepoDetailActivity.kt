package com.thecavga.querygit.ui.repodetail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.thecavga.querygit.R
import com.thecavga.querygit.base.BaseActivity
import com.thecavga.querygit.ui.repo.model.Repo
import com.thecavga.querygit.ui.repodetail.contract.RepoDetailContract
import com.thecavga.querygit.ui.repodetail.presenter.RepoDetailPresenter
import kotlinx.android.synthetic.main.activity_repo.toolbarLayout
import kotlinx.android.synthetic.main.repo_detail_layout.*
import kotlinx.android.synthetic.main.toolbar.view.*


@Suppress("UNCHECKED_CAST")
class RepoDetailActivity : BaseActivity(),  RepoDetailContract.View, View.OnClickListener {

    private var sharedPreferences: SharedPreferences? = null

    companion object {
        val REPO_ID = "repo_id"
        val REPO_NAME = "repo_name"
        val AVATAR_URL = "avatar_url"
        val STAR_COUNT = "star_count"
        val OPEN_ISSUES = "open_issues"
        val OWNER_USERNAME = "owner_username"
        val PREFS_FILENAME = "repo"
        @JvmStatic
        fun intent(context: Context, repo: Repo): Intent {
            val intent = Intent(context, RepoDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putInt(REPO_ID, repo.id)
            bundle.putString(REPO_NAME, repo.name)
            bundle.putString(AVATAR_URL, repo.owner.avatar_url)
            bundle.putString(OWNER_USERNAME, repo.owner.login)
            intent.putExtras(bundle)
            return intent
        }
    }

    private var repoDetailPresenter = RepoDetailPresenter(this)
    private var repoId: String? = ""

    override val layoutId: Int = R.layout.repo_detail_layout

    private fun initToolbar(repoId: String?){
        toolbarLayout.tvToolbarTitle.text = intent.extras?.getString(REPO_NAME)
        toolbarLayout.imgToolbarBack.visibility = View.VISIBLE
        toolbarLayout.imgToolbarStar.visibility = View.VISIBLE
        toolbarLayout.imgToolbarBack.setOnClickListener(this)
        toolbarLayout.imgToolbarStar.setOnClickListener(this)
        initializeStar(repoId)
    }


    @SuppressLint("CheckResult", "SetTextI18n")
    private fun init(){
        sharedPreferences = getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val bundle =intent.extras
        repoId =  bundle?.getInt(REPO_ID).toString()
        initToolbar(repoId)

        Log.w("avatar_url", bundle?.getString(AVATAR_URL))
        Glide.with(this)
            .load(bundle?.getString(AVATAR_URL))
            .error(R.drawable.ic_launcher_foreground)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imgRepoImage)

        tvOwner.text = bundle?.getString(OWNER_USERNAME)
        tvStars.text = getString(R.string.stars) +" "+ bundle?.getInt(STAR_COUNT)
        tvOpenIssues.text = getString(R.string.open_issues)+" "+ bundle?.getInt(OPEN_ISSUES)

    }


    override fun initializeDagger() {
       ////Object may be provided by Dagger structure
    }

    override fun initializePresenter() {
        repoDetailPresenter.setView(this)
        super.presenter = repoDetailPresenter
        init()
    }

    override fun onClick(view: View?) {
    when(view?.id){
        R.id.imgToolbarBack ->finish()
        R.id.imgToolbarStar -> handleLike(repoId)
    }
    }

    @SuppressLint("CommitPrefEdits")
    private fun handleLike(id: String?){
        if(!id.isNullOrEmpty()) {
            val isLiked = sharedPreferences!!.getBoolean(id.toString(), false)
            if (isLiked) {
                sharedPreferences?.edit()!!.putBoolean(id.toString(), false)
            } else {
                sharedPreferences?.edit()!!.putBoolean(id.toString(), true)
            }
            setStar(!isLiked)
            sharedPreferences?.edit()!!.commit()
        }
        else {
            Toast.makeText(this, "id is  null", Toast.LENGTH_LONG).show()
        }

    }


    private fun setStar(isLiked: Boolean){
        if(isLiked){
            toolbarLayout.imgToolbarStar.setImageResource(R.drawable.ic_star_selected)
    }
        else{
            toolbarLayout.imgToolbarStar.setImageResource(R.drawable.ic_star_unselected)
        }
    }

    private fun initializeStar(id: String?){
        val isLiked = sharedPreferences!!.getBoolean(id.toString(), false)
        if(isLiked){
            toolbarLayout.imgToolbarStar.setImageResource(R.drawable.ic_star_selected)
        }

        else{
            toolbarLayout.imgToolbarStar.setImageResource(R.drawable.ic_star_unselected)
        }
    }

}
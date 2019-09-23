package com.thecavga.querygit.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder


abstract class BaseActivity : AppCompatActivity(), BaseView{

     lateinit var presenter: BasePresenter<*>

    private var unbinder: Unbinder? = null

    abstract val layoutId: Int

    protected abstract fun initializeDagger()

    protected abstract fun initializePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        unbinder = ButterKnife.bind(this)
        initializeDagger()
        initializePresenter()
        presenter.initialize(intent.extras)
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }


    override fun onStop() {
        super.onStop()
        presenter.finalizeView()
    }


    override fun onDestroy() {
        super.onDestroy()
        unbinder?.unbind()
    }
}
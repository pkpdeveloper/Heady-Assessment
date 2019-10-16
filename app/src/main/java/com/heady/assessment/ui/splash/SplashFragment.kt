package com.heady.assessment.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.heady.assessment.R
import com.heady.assessment.data.SyncManager
import com.heady.assessment.network.response.ResponseData
import com.heady.assessment.presenter.splash.SplashPresenter
import com.heady.assessment.ui.main.MainActivity
import com.heady.assessment.view.splash.SplashView
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SplashFragment : DaggerFragment(), SplashView {
    @Inject
    internal lateinit var syncManger: SyncManager
    @Inject
    lateinit var presenter: SplashPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var labelTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progressBar)
        labelTextView = view.findViewById(R.id.fetching_data_label)
        presenter.setView(this)
        presenter.fetchData(syncManger)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE

    }

    override fun onSuccess(responseData: ResponseData) {
        Intent(activity, MainActivity::class.java).apply {
            startActivity(this)
            activity?.finish()
        }
    }

    override fun onError() {
        labelTextView.text = "Unable to start the application please check Internet connection"
    }
}
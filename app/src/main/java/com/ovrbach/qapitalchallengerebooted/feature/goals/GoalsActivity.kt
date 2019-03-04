package com.ovrbach.qapitalchallengerebooted.feature.goals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ovrbach.qapitalchallengerebooted.R
import com.ovrbach.qapitalchallengerebooted.domain.Response
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.helper.hide
import com.ovrbach.qapitalchallengerebooted.helper.show
import com.ovrbach.qapitalchallengerebooted.inject.ViewModelFactory
import com.ovrbach.qapitalchallengerebooted.presentation.GoalsViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.fragment_goals.*
import javax.inject.Inject

class GoalsActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    
    private lateinit var goalsAdapter: BrowseGoalsAdapter
    private lateinit var viewModel: GoalsViewModel
   
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_goals)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(GoalsViewModel::class.java)

        goalsAdapter = BrowseGoalsAdapter()

        goals_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = goalsAdapter
        }

        setSupportActionBar(goals_toolbar)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getProjects().observe(this, Observer { response ->
            when (response) {
                is Response.Success<List<Goal>> -> onSuccess(response.data)
                is Response.Error -> onError(response.error)
                is Response.Waiting -> onLoading()
            }
        })
    }

    private fun onSuccess(goals: List<Goal>) {
        progress.hide()
        error.hide()

        goals_recycler.show()
        goalsAdapter.submitList(goals)
    }

    private fun onError(e: Throwable) {
        e.printStackTrace()
        progress.hide()
        goals_recycler.hide()

        error?.apply {
            text = e.localizedMessage
            show()
        }
    }

    private fun onLoading() {
        goals_recycler.hide()
        error.hide()

        progress.show()
    }
    
}

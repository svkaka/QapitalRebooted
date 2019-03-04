package com.ovrbach.qapitalchallengerebooted.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ovrbach.qapitalchallengerebooted.domain.Response
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.interactor.goals.GetGoals
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class GoalsViewModel @Inject constructor(
    private val getGoalsUseCase: GetGoals
) : ViewModel() {

    private val liveData: MutableLiveData<Response<List<Goal>>> = MutableLiveData()

    override fun onCleared() {
        getGoalsUseCase.dispose()
        super.onCleared()
    }

    fun getProjects(): LiveData<Response<List<Goal>>> {
        if (liveData.value == null) {
            fetchProjects()
        }
        return liveData
    }

    fun fetchProjects() {
        getGoalsUseCase.execute(GetGoalsSubscriber())
        liveData.postValue(Response.Waiting)
    }

    inner class GetGoalsSubscriber : DisposableObserver<List<Goal>>() {

        override fun onComplete() {}

        override fun onNext(goals: List<Goal>) {
            liveData.postValue(Response.Success(goals))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Response.Error(e))
        }

    }
}
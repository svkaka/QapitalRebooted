package com.ovrbach.qapitalchallengerebooted.remote.service

import com.ovrbach.qapitalchallengerebooted.remote.entity.GoalsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface GoalsService {

    @GET("savingsgoals")
    fun getSavedGoals(): Observable<GoalsResponse>

}
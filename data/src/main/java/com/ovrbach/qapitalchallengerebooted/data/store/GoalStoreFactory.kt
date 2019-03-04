package com.ovrbach.qapitalchallengerebooted.data.store

import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsLocal
import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsRemote
import javax.inject.Inject

open class GoalStoreFactory @Inject constructor(
    private val goalsLocalStore: GoalsLocal,
    private val goalsRemoteStore: GoalsRemote
) {
    open fun getLocalStore() = goalsLocalStore
    open fun getRemoteStore() = goalsRemoteStore
}
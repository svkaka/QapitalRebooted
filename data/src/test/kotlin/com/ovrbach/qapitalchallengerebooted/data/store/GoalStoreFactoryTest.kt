package com.ovrbach.qapitalchallengerebooted.data.store

import com.nhaarman.mockitokotlin2.mock
import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsLocal
import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsRemote
import org.junit.Test
import kotlin.test.assertEquals

class GoalStoreFactoryTest {

    private val local = mock<GoalsLocal>()
    private val remote = mock<GoalsRemote>()

    private val goalStoreFactory = GoalStoreFactory(local, remote)

    @Test
    fun getLocalStore() {
        val actual = goalStoreFactory.getLocalStore()
        assertEquals(local, actual)
    }

    @Test
    fun getRemoteStore() {
        val actual = goalStoreFactory.getRemoteStore()
        assertEquals(remote, actual)
    }
}
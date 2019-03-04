package com.ovrbach.qapitalchallengerebooted.test

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

inline fun <T> whenever(methodCall: T): OngoingStubbing<T> {
    return Mockito.`when`(methodCall)!!
}
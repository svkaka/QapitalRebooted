package com.ovrbach.qapitalchallengerebooted.domain

//sealed class Response<T>(open val meta: Meta) {
//    data class Success<T>(val data: T, override val meta: Meta) : Response<T>(meta)
//    data class Error(val error: Throwable, override val meta: Meta) : Response<Nothing>(meta)
//    data class Waiting(override val meta: Meta) : Response<Nothing>(meta)
//}
//
//abstract class Meta(val source: Source, val params: Params? = null)
//
//enum class Source {
//    LOCAL,
//    REMOTE
//}
//
//abstract class Params()
//
//class FetchFeedParams(val id: GoalId) : Params()


sealed class Response<out T : Any> {
    data class Success<out T : Any>(val data: T) : Response<T>()
    data class Error(val error: Throwable) : Response<Nothing>()
    object Waiting : Response<Nothing>()

    fun isSuccess() = this is Success<*>
    fun isWaiting() = this is Waiting
    fun isError() = this is Error
}
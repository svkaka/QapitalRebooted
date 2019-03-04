package com.ovrbach.qapitalchallengerebooted.remote.service

import com.ovrbach.qapitalchallengerebooted.remote.util.UnitTestingHttpInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class GoalsServiceFactory {
    companion object {
        fun makeGoalsService(isDebug: Boolean, isUnitTesting: Boolean): GoalsService {
            val okHttpClient = createOkHttpClient(isDebug, isUnitTesting)
            return createGoalsService(okHttpClient)
        }

        private fun createGoalsService(okHttpClient: OkHttpClient): GoalsService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            return retrofit.create(GoalsService::class.java)
        }

        private fun createOkHttpClient(isDebug: Boolean, isUnitTesting: Boolean): OkHttpClient {
            val builder = OkHttpClient.Builder()
                .apply {
                    connectTimeout(120, TimeUnit.SECONDS)
                    readTimeout(120, TimeUnit.SECONDS)

                    if (isDebug) {
                        addInterceptor(createLoggingInterceptor(isDebug))
                    }
                    if (isUnitTesting) {
                        addInterceptor(createUnitTestInterceptor())
                    }
                }
            return builder.build()
        }

        private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
            val logger = HttpLoggingInterceptor()
            logger.level = if (isDebug) {
                HttpLoggingInterceptor.Level.HEADERS
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
            return logger
        }

        private fun createUnitTestInterceptor(): UnitTestingHttpInterceptor {
            return UnitTestingHttpInterceptor()
        }
    }
}
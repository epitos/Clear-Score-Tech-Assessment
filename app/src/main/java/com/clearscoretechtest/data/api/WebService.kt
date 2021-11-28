package com.clearscoretechtest.data.api

import com.clearscoretechtest.utils.Const.CREDIT_SCORE
import retrofit2.Response
import retrofit2.http.GET

interface WebService {

    @GET(CREDIT_SCORE)
    suspend fun getCreditScore(): Response<com.clearscoretechtest.data.model.Response>
}
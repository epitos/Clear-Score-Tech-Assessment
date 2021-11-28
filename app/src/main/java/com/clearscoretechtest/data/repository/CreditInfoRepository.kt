package com.clearscoretechtest.data.repository

import com.clearscoretechtest.data.api.WebService

class CreditInfoRepository(private val webService: WebService) {

    suspend fun getCreditScore() = webService.getCreditScore()
}
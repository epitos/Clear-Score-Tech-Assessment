package com.clearscoretechtest.data.model

import com.squareup.moshi.Json

data class Response(
    @Json(name = "accountIDVStatus")
    val accountIDVStatus: String,
    @Json(name = "augmentedCreditScore")
    val augmentedCreditScore: Any?,
    @Json(name = "coachingSummary")
    val coachingSummary: CoachingSummary,
    @Json(name = "creditReportInfo")
    val creditReportInfo: CreditReportInfo,
    @Json(name = "dashboardStatus")
    val dashboardStatus: String,
    @Json(name = "personaType")
    val personaType: String
)
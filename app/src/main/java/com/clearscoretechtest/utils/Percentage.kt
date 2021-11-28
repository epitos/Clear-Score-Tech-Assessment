package com.clearscoretechtest.utils

import com.clearscoretechtest.utils.Const.MAX_CREDIT_SCORE
import kotlin.math.round

object Percentage {

    fun calculateCreditScorePercentage(creditScore: Int) = round(creditScore.toFloat() /
            MAX_CREDIT_SCORE * 100)
}
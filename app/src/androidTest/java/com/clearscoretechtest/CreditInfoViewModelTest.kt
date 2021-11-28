package com.clearscoretechtest

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.clearscoretechtest.data.api.WebService
import com.clearscoretechtest.data.model.CoachingSummary
import com.clearscoretechtest.data.model.CreditReportInfo
import com.clearscoretechtest.data.model.Response
import com.clearscoretechtest.data.repository.CreditInfoRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class CreditInfoViewModelTest {

    private lateinit var creditInfoRepository: CreditInfoRepository
    private val server = MockWebServer()
    private val testBaseUrl = "/"
    private val body ="{\n" +
            "  \"accountIDVStatus\": \"PASS\",\n" +
            "  \"creditReportInfo\": {\n" +
            "    \"score\": 514,\n" +
            "    \"scoreBand\": 4,\n" +
            "    \"clientRef\": \"CS-SED-655426-708782\",\n" +
            "    \"status\": \"MATCH\",\n" +
            "    \"maxScoreValue\": 700,\n" +
            "    \"minScoreValue\": 0,\n" +
            "    \"monthsSinceLastDefaulted\": -1,\n" +
            "    \"hasEverDefaulted\": false,\n" +
            "    \"monthsSinceLastDelinquent\": 1,\n" +
            "    \"hasEverBeenDelinquent\": true,\n" +
            "    \"percentageCreditUsed\": 44,\n" +
            "    \"percentageCreditUsedDirectionFlag\": 1,\n" +
            "    \"changedScore\": 0,\n" +
            "    \"currentShortTermDebt\": 13758,\n" +
            "    \"currentShortTermNonPromotionalDebt\": 13758,\n" +
            "    \"currentShortTermCreditLimit\": 30600,\n" +
            "    \"currentShortTermCreditUtilisation\": 44,\n" +
            "    \"changeInShortTermDebt\": 549,\n" +
            "    \"currentLongTermDebt\": 24682,\n" +
            "    \"currentLongTermNonPromotionalDebt\": 24682,\n" +
            "    \"currentLongTermCreditLimit\": null,\n" +
            "    \"currentLongTermCreditUtilisation\": null,\n" +
            "    \"changeInLongTermDebt\": -327,\n" +
            "    \"numPositiveScoreFactors\": 9,\n" +
            "    \"numNegativeScoreFactors\": 0,\n" +
            "    \"equifaxScoreBand\": 4,\n" +
            "    \"equifaxScoreBandDescription\": \"Excellent\",\n" +
            "    \"daysUntilNextReport\": 9\n" +
            "   },\n" +
            "   \"dashboardStatus\": \"PASS\",\n" +
            "   \"personaType\": \"INEXPERIENCED\",\n" +
            "    \"coachingSummary\": {\n" +
            "    \"activeTodo\": false,\n" +
            "    \"activeChat\": true,\n" +
            "    \"numberOfTodoItems\": 0,\n" +
            "    \"numberOfCompletedTodoItems\": 0,\n" +
            "    \"selected\": true\n" +
            "  },\n" +
            "  \"augmentedCreditScore\": null\n" +
            "}"

    @Before
    fun setUp() {
        server.start(8080)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .build()

        val webService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(server.url(testBaseUrl))
            .client(okHttpClient)
            .build()
            .create(WebService::class.java)

        creditInfoRepository = CreditInfoRepository(webService)
    }

    @Test
    fun testSuccessfulResponse() {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        mockResponse.setBody(body)
        server.enqueue(mockResponse)

        runBlocking {
            val actual = creditInfoRepository.getCreditScore().body()

            val expected = Response(
                    accountIDVStatus = "PASS",
                    augmentedCreditScore = null,
                    coachingSummary =
                    CoachingSummary(activeChat=true, activeTodo=false, numberOfCompletedTodoItems=0,
                    numberOfTodoItems=0, selected=true),
                creditReportInfo=CreditReportInfo(changeInLongTermDebt=-327, changeInShortTermDebt=549,
                    changedScore=0, clientRef="CS-SED-655426-708782",
                    currentLongTermCreditLimit=null, currentLongTermCreditUtilisation=null,
                    currentLongTermDebt=24682, currentLongTermNonPromotionalDebt=24682,
                    currentShortTermCreditLimit=30600, currentShortTermCreditUtilisation=44,
                    currentShortTermDebt=13758, currentShortTermNonPromotionalDebt=13758,
                    daysUntilNextReport=9, equifaxScoreBand=4, equifaxScoreBandDescription="Excellent",
                    hasEverBeenDelinquent=true, hasEverDefaulted=false, maxScoreValue=700, minScoreValue=0,
                    monthsSinceLastDefaulted=-1, monthsSinceLastDelinquent=1, numNegativeScoreFactors=0,
                    numPositiveScoreFactors=9, percentageCreditUsed=44,
                    percentageCreditUsedDirectionFlag=1, score=514, scoreBand=4, status="MATCH",
                    creditScorePercentage=0f),
                dashboardStatus="PASS", personaType="INEXPERIENCED")

            assertEquals(expected, actual)
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}
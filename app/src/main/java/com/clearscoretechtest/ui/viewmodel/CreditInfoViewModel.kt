package com.clearscoretechtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clearscoretechtest.data.model.CreditReportInfo
import com.clearscoretechtest.data.repository.CreditInfoRepository
import com.clearscoretechtest.utils.NetworkHelper
import com.clearscoretechtest.utils.Percentage
import com.clearscoretechtest.utils.Resource
import kotlinx.coroutines.launch

class CreditInfoViewModel(private val creditInfoRepository: CreditInfoRepository,
                          private val networkHelper: NetworkHelper): ViewModel() {

    private val _creditReportInfo = MutableLiveData<Resource<CreditReportInfo>>()
    val creditReportInfo: LiveData<Resource<CreditReportInfo>>
        get() = _creditReportInfo

    init {
        getCreditInfo()
    }

    private fun getCreditInfo() {
        viewModelScope.launch {
            _creditReportInfo.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                creditInfoRepository.getCreditScore().let {
                    if (it.isSuccessful) {
                        val creditReportInfo = it.body()!!.creditReportInfo
                        creditReportInfo.creditScorePercentage =
                            Percentage.calculateCreditScorePercentage(creditReportInfo.score)
                        _creditReportInfo.postValue(Resource.success(creditReportInfo))
                    } else {
                        _creditReportInfo.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else {
                _creditReportInfo.postValue(Resource.error("No internet connection", null))
            }
        }
    }
}
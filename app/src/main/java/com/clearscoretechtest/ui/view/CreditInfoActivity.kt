package com.clearscoretechtest.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.clearscoretechtest.R
import com.clearscoretechtest.ui.viewmodel.CreditInfoViewModel
import com.clearscoretechtest.utils.Percentage.calculateCreditScorePercentage
import com.clearscoretechtest.utils.Status
import kotlinx.android.synthetic.main.activity_creditinfo.*
import org.koin.android.viewmodel.ext.android.viewModel

class CreditInfoActivity : AppCompatActivity() {

    private val creditInfoViewModel: CreditInfoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creditinfo)
        setObserver()
    }

    private fun setObserver() {
        creditInfoViewModel.creditReportInfo.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    it.data?.let { creditScoreInfo ->
                        val animationDuration = 2500
                        credit_score_bar.setProgressWithAnimation(
                            creditScoreInfo.creditScorePercentage, animationDuration)
                        credit_score.text = creditScoreInfo.score.toString()
                    }
                }
                Status.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
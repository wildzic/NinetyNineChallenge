package com.wildog.ninetyninechallenge.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import com.wildog.ninetyninechallenge.ApiManager.ApiService
import com.wildog.ninetyninechallenge.Commons.Controllers.RefreshableActivity
import com.wildog.ninetyninechallenge.Model.Company
import com.wildog.ninetyninechallenge.R
import java.io.Serializable

import kotlinx.android.synthetic.main.activity_company_detail.*

class CompanyDetailActivity : RefreshableActivity() {

    private val apiService = ApiService()
    private lateinit var currentCompany: Company

    companion object {
        private const val INTENT_COMPANY = "company"

        fun newIntent(context: Context, company: Company): Intent {
            val intent = Intent(context, CompanyDetailActivity::class.java)
            intent.putExtra(INTENT_COMPANY, company as Serializable)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_detail)

        currentCompany = intent.getSerializableExtra(INTENT_COMPANY) as Company

        supportActionBar!!.title = currentCompany.name
    }

    private fun showCompany() {
        apiService.apiClient.getCompanyById(currentCompany.id).enqueue {
            onResponse = {
                it.body()?.let {
                    with(it) {
                        txtSharePrice.text = sharePrice.toString()
                        txtDescription.text = description
                        txtCountry.text = country
                    }
                }
            }

            onFailure = {
                AlertDialog.Builder(this@CompanyDetailActivity).setTitle(resources.getString(R.string.unexpectedError)).setMessage(it?.localizedMessage).show()
            }
        }
    }

    override fun refreshActivity() {
        showCompany()
    }

}
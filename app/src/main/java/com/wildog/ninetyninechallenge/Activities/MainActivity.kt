package com.wildog.ninetyninechallenge.Activities

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.wildog.ninetyninechallenge.Adapters.CompaniesAdapter
import com.wildog.ninetyninechallenge.ApiManager.ApiService
import com.wildog.ninetyninechallenge.Commons.Controllers.RefreshableActivity
import com.wildog.ninetyninechallenge.Model.Company
import com.wildog.ninetyninechallenge.R

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : RefreshableActivity() {

    private val apiService = ApiService()
    lateinit var companiesAdapter: CompaniesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.title = resources.getString(R.string.ninetyNineMobileChallenge)

        initAdapter()
    }

    private fun initAdapter(){
        val layoutManager = LinearLayoutManager(this)
        recyclerCompanies.layoutManager = layoutManager
        companiesAdapter = CompaniesAdapter {
            showDetailCompany(it)
        }
        recyclerCompanies.adapter = companiesAdapter
    }

    override fun refreshActivity() {
        getCompanies()
    }

    private fun getCompanies() {
        apiService.apiClient.getCountries().enqueue {

            onResponse = {
                val sortedCompanies = it.body()!!.sortedWith(compareBy({ it.sharePrice }))
                companiesAdapter.replaceItems(ArrayList(sortedCompanies))
            }

            onFailure = {
                AlertDialog.Builder(this@MainActivity).setTitle(resources.getString(R.string.unexpectedError)).setMessage(it?.localizedMessage).show()
            }
        }
    }

    private fun showDetailCompany(company: Company) {
        val intent = CompanyDetailActivity.newIntent(this, company)
        startActivity(intent)
    }
}
package com.wildog.ninetyninechallenge.Adapters

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wildog.ninetyninechallenge.Model.Company
import com.wildog.ninetyninechallenge.R
import com.wildog.ninetyninechallenge.Holders.CompanyHolder

class CompaniesAdapter(private val mListener: (Company) -> Unit): RecyclerView.Adapter<CompanyHolder>() {

    private var arrayData: ArrayList<Company> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyHolder {
        return CompanyHolder(parent.inflate(R.layout.item_company, false))
    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun onBindViewHolder(companyHolder: CompanyHolder, position: Int) {
        val itemCompany = arrayData[position]

        companyHolder.bindItem(itemCompany, mListener)
    }

    override fun getItemCount(): Int {
        return arrayData.size
    }

    fun replaceItems(companies: ArrayList<Company>){
        arrayData.clear()
        arrayData.addAll(companies)

        notifyDataSetChanged()
    }

}
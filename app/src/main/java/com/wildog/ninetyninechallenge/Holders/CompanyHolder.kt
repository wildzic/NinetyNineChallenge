package com.wildog.ninetyninechallenge.Holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.wildog.ninetyninechallenge.Model.Company

import kotlinx.android.synthetic.main.item_company.view.*

class CompanyHolder (v: View) : RecyclerView.ViewHolder(v) {

    private var view: View = v

    fun bindItem(company: Company, listener: (Company) -> Unit) = with(view) {
        with(company){
            txtName.text = name
            txtRic.text = ric
            txtSharePrice.text = sharePrice.toString()
            setOnClickListener { listener(company) }
        }

    }

}
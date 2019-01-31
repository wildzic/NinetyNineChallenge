package com.wildog.ninetyninechallenge

import com.wildog.ninetyninechallenge.Model.Company
import org.junit.Assert
import org.junit.Test

class ModelUnitTest {

    val JSON_COMPANY = "{" +
            "\"id\":1," +
            "\"name\":\"Apple Inc.\"," +
            "\"ric\":\"APPL\"," +
            "\"sharePrice\":220.587" +
        "}"

    @Test
    fun companyParsingTest(){
        val gson = Gson()
        val company = gson.fromJson(JSON_COMPANY, Company::class.java)

        Assert.assertEquals(company.name, "Apple Inc.")
        Assert.assertEquals(company.ric, "APPL")
        Assert.assertEquals(company.sharePrice, "220.587".toDouble(), 0.0)
    }

}
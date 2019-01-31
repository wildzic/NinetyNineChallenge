package com.wildog.ninetyninechallenge.Model

import java.io.Serializable

data class Company(var id: Int,
                   var name: String,
                   var ric: String,
                   var sharePrice: Double,
                   var description: String,
                   var country: String) : Serializable
package com.heady.assessment.view.main

import com.heady.assessment.network.response.ResponseData

interface MainView {
    fun displayData(responseData: ResponseData)
    fun onError()
}
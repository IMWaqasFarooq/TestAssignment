package com.example.testassignment.data;


import com.example.testassignment.ui.home.model.RateModel;
import com.example.testassignment.utils.constants.AppConstants;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.POST;

public interface APIInterface {



    @POST(AppConstants.SERVER_URL_EXT + "USD")
    Call<RateModel> getRateList () ;

}

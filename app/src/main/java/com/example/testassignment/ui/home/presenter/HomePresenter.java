package com.example.testassignment.ui.home.presenter;

import com.example.testassignment.data.APIClient;
import com.example.testassignment.data.APIInterface;
import com.example.testassignment.ui.home.model.RateModel;
import com.example.testassignment.ui.home.view.IHomeView;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements IHomePresenter{

    IHomeView iHomeView;
    APIInterface apiInterface;


    public HomePresenter(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }



    @Override
    public void getRateList() {
        Call<RateModel> call = apiInterface.getRateList();
        call.enqueue(new Callback<RateModel>() {
            @Override
            public void onResponse(Call<RateModel> call, Response<RateModel> response) {
//                Map<String, Object> map = new HashMap<String,Object>();
//                ArrayList<String> keyValue = new ArrayList<>();
//
//                try{
//                    JSONObject jsonObject = response.body().getJSONObject("rates");
//
//                    Iterator<String> keys = jsonObject.keys();
//                    while(keys.hasNext()) {
//                        String key = keys.next();
//                        keyValue.add(key);
//                        Object value = jsonObject.get(key);
//                        map.put(key, value);
//
//                    }
//                }catch (Exception e){
//
//                }
                iHomeView.onResultRates(response.body().result,response.body());


            }

            @Override
            public void onFailure(Call<RateModel> call, Throwable t) {
                iHomeView.onResultRates("Falied",null);
            }
        });
    }

}

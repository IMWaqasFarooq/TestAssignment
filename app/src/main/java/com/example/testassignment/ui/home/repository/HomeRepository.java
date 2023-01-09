package com.example.testassignment.ui.home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testassignment.data.APIClient;
import com.example.testassignment.data.APIInterface;
import com.example.testassignment.ui.home.model.RateModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    APIInterface apiInterface;

    public HomeRepository(){
        apiInterface = APIClient.getClient().create(APIInterface .class);

    }

   public LiveData<RateModel> getCurrencyRates(){

       final MutableLiveData<RateModel> data = new MutableLiveData<>();

       Call<RateModel> call = apiInterface.getRateList();
       call.enqueue(new Callback<RateModel>() {
           @Override
           public void onResponse(Call<RateModel> call, Response<RateModel> response) {

               data.setValue(response.body());


           }

           @Override
           public void onFailure(Call<RateModel> call, Throwable t) {
               data.setValue(null);
           }
       });

       return data;

   }


}

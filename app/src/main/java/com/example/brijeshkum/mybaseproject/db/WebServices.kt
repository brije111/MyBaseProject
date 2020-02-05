package com.example.brijeshkum.mybaseproject.db

import com.example.brijeshkum.mybaseproject.db.model.Contact
import retrofit2.Call
import retrofit2.http.GET

//import com.example.aptcnew.db.model.Individual;
/**
 *
 * Call<OffensiveArea> call = BaseActivity.apiService.createUser(area.getLat1(),
 * area.getLng1(), area.getLat2(), area.getLng2(), area.getRadius());
 * call.enqueue(new Callback<OffensiveArea>() {
 * @Override
 * public void onResponse(Call<OffensiveArea> call, Response<OffensiveArea> response) {
 * Log.d(TAG, "onResponse: "+response.toString());
 * }
 *
 * @Override
 * public void onFailure(Call<OffensiveArea> call, Throwable t) {
 * Log.d(TAG, "onFailure: "+t.getMessage());
 * }
 * });</OffensiveArea></OffensiveArea></OffensiveArea></OffensiveArea></OffensiveArea> */
interface WebServices {
    // Request method and URL specified in the annotation
// Callback for the parsed response is the last parameter
    @GET("contact")
    fun getContacts(): Call<List<Contact?>?>
}
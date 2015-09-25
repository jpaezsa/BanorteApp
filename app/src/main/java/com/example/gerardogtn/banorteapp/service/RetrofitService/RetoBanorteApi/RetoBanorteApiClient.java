package com.example.gerardogtn.banorteapp.service.RetrofitService.RetoBanorteApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by gerardogtn on 9/23/15.
 */
public class RetoBanorteApiClient {
    private static RetoBanorteApiService apiService;

    public static RetoBanorteApiService getInstance() {
        if (apiService == null) {
            Gson gsonDateBuilder = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSSSSS")
                    .create();

            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(RetoBanorteApiConstants.URL_BASE)
                    .setConverter(new GsonConverter(gsonDateBuilder))
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .build();

            apiService = adapter.create(RetoBanorteApiService.class);
        }

        return apiService;
    }
}

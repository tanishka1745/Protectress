package com.example.protectress.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    private  static retrofit2.Retrofit retrofit=null;
    public static ApiUtilities getApiInterface()
    {
        if(retrofit==null)
        {
            retrofit=  new retrofit2.Retrofit.Builder().baseUrl(ApiUtilities.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiUtilities.class);
    }


}

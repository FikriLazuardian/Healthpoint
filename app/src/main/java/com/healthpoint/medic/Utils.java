package com.healthpoint.medic;

import com.healthpoint.medic.model.ApiError;
import com.healthpoint.medic.network.APIClient;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class Utils {
    public static ApiError converErrors(ResponseBody response){
        Converter<ResponseBody,ApiError>converter = APIClient.getRetrofit().responseBodyConverter(ApiError.class,new Annotation[0]);
        ApiError apiError = null;
        try{
            apiError = converter.convert(response);
        }catch (IOException e){
            e.printStackTrace();
        }return apiError;
    }
}

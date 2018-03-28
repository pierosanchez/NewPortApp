package com.newport.app.data.api;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by tohure on 06/11/17.
 */

@SuppressWarnings("ConstantConditions")
class ErrorUtil {

    public static ErrorResponse parseError(Response<?> response) {

        Converter<ResponseBody, ErrorResponse> converter = NewPortApiManager.getRetrofit()
                .responseBodyConverter(ErrorResponse.class, new Annotation[0]);

        ErrorResponse error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErrorResponse();
        }

        return error;
    }
}
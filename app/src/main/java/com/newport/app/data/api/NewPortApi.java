package com.newport.app.data.api;

import com.newport.app.BuildConfig;
import com.newport.app.data.models.request.PhotoRequest;
import com.newport.app.data.models.response.DirectoryResponse;
import com.newport.app.data.models.response.EventsResponse;
import com.newport.app.data.models.response.HomeResponse;
import com.newport.app.data.models.response.LoginResponse;
import com.newport.app.data.models.response.NewResponse;
import com.newport.app.data.models.response.PhotoGalleryEventResponse;
import com.newport.app.data.models.response.PhotoUploadedResponse;
import com.newport.app.data.models.response.ScheduleResponse;
import com.newport.app.data.models.response.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.newport.app.util.Constant.HEADER_AUTHORIZATION;

/**
 * Created by tohure on 06/11/17.
 */

public interface NewPortApi {
    @Headers(HEADER_AUTHORIZATION)
    @GET(BuildConfig.LOGIN)
    Call<LoginResponse> login(@Query("dni") String dni);

    @Headers(HEADER_AUTHORIZATION)
    @GET(BuildConfig.HOME)
    Call<HomeResponse> getHome(@Query("dni") String dni);

    @Headers(HEADER_AUTHORIZATION)
    @GET(BuildConfig.USER)
    Call<UserResponse> getUser(@Query("dni") String dni);

    @Headers(HEADER_AUTHORIZATION)
    @GET(BuildConfig.EVENTS)
    Call<List<EventsResponse>> getEvents();

    @Headers(HEADER_AUTHORIZATION)
    @GET(BuildConfig.NEW_DETAIL)
    Call<NewResponse> getNewDetail(@Path("id") int idNew);

    @Headers(HEADER_AUTHORIZATION)
    @GET(BuildConfig.GALLERY_PHOTOS_EVENT)
    Call<List<PhotoGalleryEventResponse>> getPhotosEvent(@Path("id") int idNew);

    @Headers(HEADER_AUTHORIZATION)
    @POST(BuildConfig.SEND_PHOTO)
    Call<PhotoUploadedResponse> sendPhoto(@Body PhotoRequest photo);

    @Headers(HEADER_AUTHORIZATION)
    @GET(BuildConfig.DIRECTORY)
    Call<List<DirectoryResponse>> getDirectory();

    @Headers(HEADER_AUTHORIZATION)
    @GET(BuildConfig.SCHEDULES)
    Call<List<ScheduleResponse>> getSchedules();
}
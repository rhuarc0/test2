package com.zennex.trl3lg.presentation.common.di.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.zennex.trl3lg.BuildConfig;
import com.zennex.trl3lg.data.rest.response.book.FetchBookListResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchQueueBooksResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchRentalGroupsResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchReviewsResponse;
import com.zennex.trl3lg.data.util.gson.deserialize.FetchBooksDeserializeAdapter;
import com.zennex.trl3lg.data.util.gson.deserialize.FetchQueueDeserializeAdapter;
import com.zennex.trl3lg.data.util.gson.deserialize.FetchRentalGroupsDeserializeAdapter;
import com.zennex.trl3lg.data.util.gson.deserialize.FetchReviewsDeserializeAdapter;
import com.zennex.trl3lg.presentation.common.di.app.AppModule;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nikita on 14.03.2017.
 */

@Module(includes = AppModule.class)
public class NetworkModule {


    public static final String TIME_FORMAT = "dd-MM-yyyy'T'HH:mm:ss";

    @NonNull
    @Provides
    protected Retrofit provideAuthenticationRetrofit(
            OkHttpClient okHttpClient,
            Retrofit.Builder baseRetrofitBuilder
    ) {
        return baseRetrofitBuilder
                .client(okHttpClient)
                .build();
    }


    @NonNull
    @Provides
    public Retrofit.Builder provideBaseRetrofitBuilder(Gson gson
    ) {

        return new Retrofit.Builder()
                .baseUrl("http://www.websiteforge.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    @NonNull
    @Provides
    public OkHttpClient.Builder provideBaseOkHttpBuilder() {

        LoggingInterceptor loggingInterceptor = new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BODY)
                .log(Log.INFO)
                .request("Request")
                .response("Response")
                .build();

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor);
    }

    @NonNull
    @Provides
    public OkHttpClient provideAuthenticationOkHttpClient(OkHttpClient.Builder baseBuilder, Context context) {
        return baseBuilder.build();
    }

    @NonNull
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat(TIME_FORMAT)
                .registerTypeAdapter(new TypeToken<List<FetchRentalGroupsResponse>>() {
                }.getType(), new FetchRentalGroupsDeserializeAdapter())
                .registerTypeAdapter(new TypeToken<List<FetchBookListResponse>>() {
                }.getType(), new FetchBooksDeserializeAdapter())
                .registerTypeAdapter(new TypeToken<List<FetchQueueBooksResponse>>() {
                }.getType(), new FetchQueueDeserializeAdapter())
                .registerTypeAdapter(new TypeToken<List<FetchReviewsResponse>>() {
                }.getType(), new FetchReviewsDeserializeAdapter())
                .create();
    }

    //region provides services and repos

}

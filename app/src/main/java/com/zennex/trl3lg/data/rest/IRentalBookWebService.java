package com.zennex.trl3lg.data.rest;

import com.zennex.trl3lg.data.entity.dto.BookDto;
import com.zennex.trl3lg.data.rest.request.book.ActivateAudioBookRequest;
import com.zennex.trl3lg.data.rest.request.book.AddBookRequest;
import com.zennex.trl3lg.data.rest.request.book.ChangeAudioBookListOrderRequest;
import com.zennex.trl3lg.data.rest.request.book.FetchBookByIdRequest;
import com.zennex.trl3lg.data.rest.request.book.FetchBookListRequest;
import com.zennex.trl3lg.data.rest.request.book.FetchHistoryRequest;
import com.zennex.trl3lg.data.rest.request.book.FetchQueueRequest;
import com.zennex.trl3lg.data.rest.request.book.FetchRentalGroupsRequest;
import com.zennex.trl3lg.data.rest.request.book.RemoveAudioBookRequest;
import com.zennex.trl3lg.data.rest.request.book.RenewAudioBookRequest;
import com.zennex.trl3lg.data.rest.request.book.StorePositionRequest;
import com.zennex.trl3lg.data.rest.response.BaseResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchBookListResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchAudioBooksQueueResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchCDBooksQueueResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchRentalGroupsResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IRentalBookWebService {

    //region Fetch Books

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<FetchRentalGroupsResponse>> fetchRentalGroups(@Body List<FetchRentalGroupsRequest> request);

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<FetchBookListResponse>> fetchBooks(@Body List<FetchBookListRequest> requests);

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<FetchAudioBooksQueueResponse>> fetchQueue(@Body List<FetchQueueRequest> requests);


    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<BaseResponse<BookDto>>> fetchBookById(@Body List<FetchBookByIdRequest> request);


    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<FetchAudioBooksQueueResponse>> fetchHistory(@Body List<FetchHistoryRequest> request);

    //endregion



    //region Add book

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<FetchAudioBooksQueueResponse>> addAudioBook(@Body List<AddBookRequest> requests);

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<FetchCDBooksQueueResponse>> addCDBook(@Body List<AddBookRequest> requests);

    //endregion



    //region Work with audio books

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<BaseResponse>> storeAudioPosition(@Body List<StorePositionRequest> requests);

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<FetchAudioBooksQueueResponse>> activateAudioBook(@Body List<ActivateAudioBookRequest> request);

    @Headers("Contnet-Type: application/json")
    @POST("core/webservice")
    Observable<List<FetchAudioBooksQueueResponse>> renewLiveItem(@Body List<RenewAudioBookRequest> request);

    @Headers("Contnet-Type: application/json")
    @POST("core/webservice")
    Observable<List<FetchAudioBooksQueueResponse>> removeAudioBook(@Body List<RemoveAudioBookRequest> request);

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<FetchAudioBooksQueueResponse>> changeAudioBookListOrder(@Body List<ChangeAudioBookListOrderRequest> request);

    //endregion




    //region Common CD/Audio

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<BaseResponse>> rateBook(@Body List<ChangeAudioBookListOrderRequest> request);

    //endregion

}

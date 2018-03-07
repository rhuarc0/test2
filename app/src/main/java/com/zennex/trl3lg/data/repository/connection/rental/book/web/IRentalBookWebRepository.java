package com.zennex.trl3lg.data.repository.connection.rental.book.web;

import com.zennex.trl3lg.domain.entities.AudioBook;
import com.zennex.trl3lg.data.entity.rest.request.FetchBookListRequest;
import com.zennex.trl3lg.data.entity.rest.request.FetchRentalGroupsRequest;
import com.zennex.trl3lg.data.entity.rest.response.FetchBookListResponse;
import com.zennex.trl3lg.data.entity.rest.response.FetchRentalGroupsResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by nikita on 26.07.17.
 */

public interface IRentalBookWebRepository {

    Observable<List<FetchRentalGroupsResponse>> getGroups(List<FetchRentalGroupsRequest> request);

    Observable<List<FetchBookListResponse>> fetchBooks(List<FetchBookListRequest> requests);

    Observable<List<AudioBook>> fetchMyAudioBooks();

}

package com.zennex.trl3lg.data.repository.connection.rental.book;

import com.zennex.trl3lg.domain.entities.AudioBook;
import com.zennex.trl3lg.data.entity.rest.request.FetchBookListRequest;
import com.zennex.trl3lg.data.entity.rest.request.FetchRentalGroupsRequest;
import com.zennex.trl3lg.data.entity.rest.response.FetchBookListResponse;
import com.zennex.trl3lg.data.entity.rest.response.FetchRentalGroupsResponse;
import com.zennex.trl3lg.data.repository.connection.rental.book.local.IRentalBookLocalRepository;
import com.zennex.trl3lg.data.repository.connection.rental.book.web.IRentalBookWebRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nikita on 26.07.17.
 */

public class RentalBookRepository implements IRentalBookRepository {

    @Inject
    IRentalBookWebRepository mRentalBookWebRepository;

    @Inject
    IRentalBookLocalRepository mRentalBookLocalRepository;

    @Inject
    public RentalBookRepository() {
    }

    @Override
    public Observable<List<FetchRentalGroupsResponse>> getGroups(List<FetchRentalGroupsRequest> request) {
        return mRentalBookWebRepository.getGroups(request);
    }

    @Override
    public Observable<List<FetchBookListResponse>> fetchBooks(List<FetchBookListRequest> requests) {
        return mRentalBookWebRepository.fetchBooks(requests);
    }

    @Override
    public Observable<List<AudioBook>> fetchMyAudioBooks() {
        return mRentalBookWebRepository.fetchMyAudioBooks();
    }
}

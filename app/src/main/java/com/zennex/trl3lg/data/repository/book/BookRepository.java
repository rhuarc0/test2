package com.zennex.trl3lg.data.repository.book;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.datasource.book.BookDataSourceRemote;
import com.zennex.trl3lg.data.entity.dto.BookDto;
import com.zennex.trl3lg.data.entity.dto.RentalGroupDto;
import com.zennex.trl3lg.domain.repository.IAuthRepository;
import com.zennex.trl3lg.data.util.StringUtils;
import com.zennex.trl3lg.data.rest.request.book.FetchBookListRequest;
import com.zennex.trl3lg.data.rest.request.book.FetchBookListRequest.BookFilter;
import com.zennex.trl3lg.data.rest.request.book.FetchRentalGroupsRequest;
import com.zennex.trl3lg.data.rest.response.BaseResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchBookListResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchRentalGroupsResponse;
import com.zennex.trl3lg.data.mapper.dtomapper.BookDtoMapperWrapper;
import com.zennex.trl3lg.data.mapper.dtomapper.RentalGroupDtoMapper;
import com.zennex.trl3lg.domain.entities.AudioBook;
import com.zennex.trl3lg.domain.entities.Book;
import com.zennex.trl3lg.domain.entities.RentalGroup;
import com.zennex.trl3lg.domain.repository.IBookRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static com.annimon.stream.Stream.of;


public class BookRepository implements IBookRepository {

    private static final String ONLY_ACTIVE = "1";
    private static final String ALL_CATEGORIES_ID = "0";

    private FetchBookListRequest.KeywordSearch mKeywordSearch;

    // Data Sources

    @Inject
    BookDataSourceRemote bookRemoteDataSource;

    @Inject
    IAuthRepository authRepository;

    // Mappers

    @Inject
    RentalGroupDtoMapper mRentalGroupDtoMapper;

    @Inject
    BookDtoMapperWrapper bookDtoMapper;


    @Inject
    public BookRepository() { }

    @Override
    public Observable<List<RentalGroup>> getGroups() {
        return bookRemoteDataSource.getGroups(createGetGroupsRequest())
                .map(getRentalGroupData())
                .map(filterGroups())
                .map(transformRentalGroups())
                .map(fixTitles());
    }

    @Override
    public Observable<List<Book>> fetchBooks(@Nullable String keyword,
                                             @Nullable String type,
                                             short quantityBooksRequested,
                                             int startingLoadPosition,
                                             String rentalGroupId) {

        return Observable.fromCallable(() -> createGetBooksRequests(keyword, type, quantityBooksRequested, startingLoadPosition, rentalGroupId))
                .flatMap(o -> bookRemoteDataSource.fetchBooks(o))
                .map(getBooksData())
                .map(transformBooks());
    }

    @Override
    public Observable<List<AudioBook>> fetchMyAudioBooks() {
        return bookRemoteDataSource.fetchMyAudioBooks();
    }

    @Override
    public Observable<List<String>> fetchQueueBookIds() {
        return bookRemoteDataSource.fetchQueueIds();
    }

    @Override
    public Observable<List<String>> fetchHistory() {
        return bookRemoteDataSource.fetchHistory();
    }


    // aux methods

    private List<FetchRentalGroupsRequest> createGetGroupsRequest() {
        return of(authRepository.getRentalModuleIds().blockingSingle())
                .map(rentalId -> {
                    String mode = FetchRentalGroupsRequest.Data.MODE_LIST;
                    return FetchRentalGroupsRequest.newInstance(rentalId, mode ,ONLY_ACTIVE);
                }).toList();
    }

    private Function<List<FetchRentalGroupsResponse>, List<List<RentalGroupDto>>> getRentalGroupData() {
        return rentalGroups -> of(rentalGroups)
                .map(BaseResponse::getData)
                .toList();
    }


    private Function<List<List<RentalGroupDto>>, List<List<RentalGroupDto>>> filterGroups() {
        return rentalGroups -> of(rentalGroups)
                .map(rentalGroupDtos -> {

                    List<RentalGroupDto> rentalGroupRootsDtos = of(rentalGroupDtos)
                            .filter(rentalGroupDto -> rentalGroupDto.getParentId().equals(ALL_CATEGORIES_ID))
                            .toList();

                    return of(rentalGroupDtos)
                            .filter(rentalGroupDto -> of(rentalGroupRootsDtos)
                                    .map(rentalGroupRootDto -> rentalGroupRootDto.getId().equals(rentalGroupDto.getParentId()))
                                    .reduce((value1, value2) -> value1 | value2)
                                    .get())
                            .toList();
                })
                .toList();
    }

    private Function<List<List<RentalGroupDto>>, List<RentalGroup>> transformRentalGroups() {
        return lists -> {
            List<RentalGroup> intermediateList = of(lists)
                    .map(rentalGroupDtos -> mRentalGroupDtoMapper.execute(rentalGroupDtos))
                    .flatMap(Stream::of)
                    .distinct()
                    .toList();

            List<RentalGroup> resultList = new ArrayList<>();
            Stream.of(intermediateList)
                    .forEach(rentalGroup -> {
                        boolean isHas = Stream.of(resultList)
                                .anyMatch(rentalGroup1 -> rentalGroup.getTitle().equals(rentalGroup1.getTitle()));
                        if (!isHas) resultList.add(rentalGroup);
                    });

            return resultList;
        };
    }


    private Function<List<RentalGroup>, List<RentalGroup>> fixTitles() {
        return rentalGroups -> Stream.of(rentalGroups)
                .map(rentalGroup -> {
                    rentalGroup.setTitle(rentalGroup.getTitle().replaceFirst("-", ""));
                    return rentalGroup;
                })
                .toList();
    }



    private List<FetchBookListRequest> createGetBooksRequests(@Nullable String keyword,
                                                              String bookType,
                                                              short quantityBooksRequested,
                                                              int startingLoadPosition,
                                                              String rentalGroupId) {
        return Stream.of(authRepository.getRentalModuleIds().blockingSingle())
                .map(moduleId -> {
                    List<BookFilter> filters = new ArrayList<>();

                    // Active
                    BookFilter filter = new BookFilter(BookFilter.FIELD_ACTIVE, "1");
                    filters.add(filter);
                    // Group Id
                    if (!StringUtils.isEmpty(rentalGroupId) && !rentalGroupId.equals("-1")) {
                        filter = new BookFilter(BookFilter.FIELD_GROUP_ID, rentalGroupId);
                        filters.add(filter);
                    }
                    // Type
                    if (!StringUtils.isEmpty(bookType)) {
                        BookFilter filterSet = new BookFilter(BookFilter.FIELD_SET, bookType);
                        filters.add(filterSet);
                    }

                    FetchBookListRequest.Data data = new FetchBookListRequest.Data();
                    data.setFilters(filters);
                    data.setCountBooks(Short.toString(quantityBooksRequested));
                    data.setStartPosition(Integer.toString(startingLoadPosition));


                    if (!TextUtils.isEmpty(keyword)) {
                        mKeywordSearch = new FetchBookListRequest.KeywordSearch();
                        mKeywordSearch.setKeyword(keyword);
                        mKeywordSearch.setType(FetchBookListRequest.KeywordSearch.TYPE_AND);
                        mKeywordSearch.setFields("code,string0,text4,text5");
                        data.setKeywordSearch(mKeywordSearch);
                    }

                    return FetchBookListRequest.newInstance(moduleId, data);
                })
                .toList();
    }

    private Function<List<FetchBookListResponse>, List<List<BookDto>>> getBooksData() {
        return responses -> Stream.of(responses)
                .map(BaseResponse::getData)
                .toList();
    }

    @SuppressWarnings("unchecked")
    private Function<List<List<BookDto>>, List<Book>> transformBooks() {
        return bookDtos -> Stream.of(bookDtos)
                .map(bookDto -> bookDtoMapper.execute(bookDto))
                .flatMap(Stream::of)
                .toList();
    }


}


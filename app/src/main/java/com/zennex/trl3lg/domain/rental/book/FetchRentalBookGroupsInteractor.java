package com.zennex.trl3lg.domain.rental.book;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.entity.RentalGroup;
import com.zennex.trl3lg.data.entity.dto.RentalGroupDto;
import com.zennex.trl3lg.data.entity.rest.request.FetchRentalGroupsRequest;
import com.zennex.trl3lg.data.entity.rest.response.BaseResponse;
import com.zennex.trl3lg.data.entity.rest.response.FetchRentalGroupsResponse;
import com.zennex.trl3lg.data.mapper.RentalBookGroupDtoMapper;
import com.zennex.trl3lg.data.repository.connection.auth.IAuthRepository;
import com.zennex.trl3lg.data.repository.connection.rental.book.IRentalBookRepository;
import com.zennex.trl3lg.domain.common.BaseInteractor;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;

import static com.annimon.stream.Stream.of;

/**
 * Created by zennex on 27.07.17.
 */

public class FetchRentalBookGroupsInteractor extends BaseInteractor<List<RentalGroup>, Void> {

    @Inject
    IRentalBookRepository mRentalBookRepository;

    @Inject
    IAuthRepository mAuthRepository;

    @Inject
    RentalBookGroupDtoMapper mRentalBookGroupDtoMapper;

    private final String ALL_CATEGORIES_ID = "0";


    @Inject
    public FetchRentalBookGroupsInteractor(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<List<RentalGroup>> buildObservable(Void aVoid) {
        return mRentalBookRepository.getGroups(createRequest())
                .map(getData())
                .map(filterGroups())
                .map(transform())
                .map(fixTitles());
    }


    private List<FetchRentalGroupsRequest> createRequest() {
        return of(mAuthRepository.getRentalModuleIds().blockingSingle())
                .map(rentalId -> {
                    FetchRentalGroupsRequest.Data data = new FetchRentalGroupsRequest.Data();
                    data.setMode(FetchRentalGroupsRequest.Data.MODE_LIST);
                    data.setOnlyActive("1");
                    return FetchRentalGroupsRequest.newInstance(rentalId, data);
                }).toList();
    }

    private Function<List<FetchRentalGroupsResponse>, List<List<RentalGroupDto>>> getData() {
        return rentalGroups -> of(rentalGroups)
                .map(BaseResponse::getData)
                .toList();
    }


    private Function<List<List<RentalGroupDto>>, List<RentalGroup>> transform() {
        return lists -> {
            List<RentalGroup> intermediateList = of(lists)
                    .map(rentalGroupDtos -> mRentalBookGroupDtoMapper.execute(rentalGroupDtos))
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


    private Function<List<RentalGroup>, List<RentalGroup>> fixTitles() {
        return rentalGroups -> Stream.of(rentalGroups)
                .map(rentalGroup -> {
                    rentalGroup.setTitle(rentalGroup.getTitle().replaceFirst("-", ""));
                    return rentalGroup;
                })
                .toList();
    }

}

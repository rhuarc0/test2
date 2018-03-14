package com.zennex.trl3lg.domain.repository;

import android.support.annotation.Nullable;

import com.zennex.trl3lg.domain.entities.AudioBook;
import com.zennex.trl3lg.domain.entities.Book;
import com.zennex.trl3lg.domain.entities.RentalGroup;

import java.util.List;

import io.reactivex.Observable;

public interface IBookRepository {
    Observable<List<RentalGroup>> getGroups();
    Observable<List<Book>> fetchBooks(@Nullable String keyword,
                                      @Nullable String type,
                                      short quantityBooksRequested,
                                      int startingLoadPosition,
                                      String rentalGroupId);
    Observable<List<AudioBook>> fetchMyAudioBooks();
}

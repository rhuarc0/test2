package com.zennex.trl3lg.data.mapper.dtomapper;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.annimon.stream.function.Supplier;
import com.zennex.trl3lg.data.mapper.base.Mapper;
import com.zennex.trl3lg.domain.entities.Book;
import com.zennex.trl3lg.data.entity.dto.BookDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by nikit on 02.08.2017.
 */

public class BookDtoMapper<S1 extends BookDto, R1 extends Book> extends Mapper<S1, R1> {

    public BookDtoMapper(@NonNull Supplier<R1> creator) {
        super(creator);
    }

    @NonNull
    @Override
    protected R1 transform(@NonNull S1 bookDto, R1 book) {
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setCode(bookDto.getCode());
        book.setStatus(bookDto.getStatus());
        book.setActive(bookDto.getActive());
        book.setAuthor(bookDto.getAuthor());
        book.setAuthor2(bookDto.getAuthor2());
        book.setAvailQoh(bookDto.getAvailQoh());
        book.setAbridgement(bookDto.getAbridgement());
        book.setBisac1(bookDto.getBisac1());
        book.setBisac2(bookDto.getBisac2());
        book.setBisac3(bookDto.getBisac3());
        book.setISBN(bookDto.getISBN());
        book.setISBN2(bookDto.getISBN2());
        book.setCopyRight(bookDto.getCopyRight());
        book.setCreated(prepareReleaseDate(Long.parseLong(bookDto.getCreated())));
        book.setModified(bookDto.getModified());
        book.setDescr(bookDto.getDescr());
        book.setFieldSet(bookDto.getFieldSet());
        book.setLanguage(bookDto.getLanguage());
        book.setLiveMediaFee(bookDto.getLiveMediaFee());
        book.setNarator(bookDto.getNarator());
        book.setPhysical(bookDto.getPhysical());
        book.setPublisher(bookDto.getPublisher());
        book.setRandomBarCode(bookDto.getRandomBarCode());
        book.setRating(bookDto.getRating());
        book.setSharpOfCDs(bookDto.getSharpOfCDs());
        book.setTrlLetterPair(bookDto.getTrlLetterPair());
        book.setWeight(bookDto.getWeight());

        if (bookDto.getImage() != null && !bookDto.getImage().isEmpty()) {
            book.setImage(bookDto.getImage().get(0));
        }

        if (bookDto.getAvailable() != null && !bookDto.getImage().isEmpty()) {
            book.setAvailable(prepareAvailable(bookDto.getAvailable().get(0)));
        }
        return book;
    }

    private String prepareReleaseDate(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        Date date = new Date(timeMillis);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd");
        return dateFormat.format(date);
    }

    private String prepareAvailable(String availbale) {
        if (availbale != null && (availbale.contains("CD") ||
                availbale.contains("CD") ||
                availbale.contains("CD") ||
                availbale.contains("CD"))) {
            return "Disc";
        }
        return availbale;
    }

}

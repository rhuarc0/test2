package com.zennex.trl3lg.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by nikit on 02.08.2017.
 */

public class Book implements Parcelable {

    public static final String FIELD_ID = "id";
    public static final String FIELD_CODE = "code";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_IMAGE = "image";
    public static final String FIELD_AVAILABLE = "available";
    public static final String FIELD_SET = "field_set";
    public static final String FIELD_ACTIVE = "field_active";
    public static final String FIELD_AUTHOR = "field_author";
    public static final String FIELD_AUTHOR2 = "field_author2";
    public static final String FIELD_TRL_LETTER_PAIR = "trl_letter_pair";
    public static final String FIELD_RANDOM_BAR_CODE = "random_bar_code";
    public static final String FIELD_COPY_RIGHT = "copy_right";
    public static final String FIELD_PUBLISHER = "publisher";
    public static final String FIELD_ISBN2 = "isbn2";
    public static final String FIELD_SHARP_OR_CDS = "sharp_or_cds";
    public static final String FIELD_CREATED = "created";
    public static final String FIELD_MODIFIED = "modified";
    public static final String FIELD_RATING = "rating";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_AVAILQOH = "availQoh";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_LIVE_MEDIA_FEE = "live_media_fee";
    public static final String FIELD_WEIGHT = "weight";
    public static final String FIELD_PHYSICAL = "physical";
    public static final String FIELD_LANGUAGE = "language";
    public static final String FIELD_NARATOR = "narator";
    public static final String FIELD_BISAC1 = "bisac1";
    public static final String FIELD_BISAC2 = "bisac2";
    public static final String FIELD_BISAC3 = "bisac3";
    public static final String FIELD_ISBN = "isbn";
    public static final String FIELD_ABRIDGMENT = "abridgment";
    public static final String FIELD_PRIMARY_GENRE = "primary_genre";

    String mId;
    String mFieldSet;
    String mActive;
    String mAuthor;
    String mAuthor2;
    String mCode;
    String mTrlLetterPair;
    String mRandomBarCode;
    String mCopyRight;
    String mPublisher;
    String mISBN2;
    String mSharpOfCDs;
    String mCreated;
    String mModified;
    float mRating;
    String mStatus;
    String mAvailQoh;
    String mTitle;
    String mImage;
    String mDescr;
    String mLiveMediaFee;
    String mWeight;
    String mPhysical;
    String mAvailable;
    String mLanguage;
    String mNarator;
    String mBisac1;
    String mBisac2;
    String mBisac3;
    String mISBN;
    String mAbridgement;
    String mPrimaryGenre;

    public Book() {
    }

    protected Book(Parcel in) {
        mId = in.readString();
        mFieldSet = in.readString();
        mActive = in.readString();
        mAuthor = in.readString();
        mAuthor2 = in.readString();
        mCode = in.readString();
        mTrlLetterPair = in.readString();
        mRandomBarCode = in.readString();
        mCopyRight = in.readString();
        mPublisher = in.readString();
        mISBN2 = in.readString();
        mSharpOfCDs = in.readString();
        mCreated = in.readString();
        mModified = in.readString();
        mRating = in.readFloat();
        mStatus = in.readString();
        mAvailQoh = in.readString();
        mTitle = in.readString();
        mImage = in.readString();
        mDescr = in.readString();
        mLiveMediaFee = in.readString();
        mWeight = in.readString();
        mPhysical = in.readString();
        mAvailable = in.readString();
        mLanguage = in.readString();
        mNarator = in.readString();
        mBisac1 = in.readString();
        mBisac2 = in.readString();
        mBisac3 = in.readString();
        mISBN = in.readString();
        mAbridgement = in.readString();
        mPrimaryGenre = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getFieldSet() {
        return mFieldSet;
    }

    public void setFieldSet(String fieldSet) {
        mFieldSet = fieldSet;
    }

    public String getActive() {
        return mActive;
    }

    public void setActive(String active) {
        mActive = active;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getAuthor2() {
        return mAuthor2;
    }

    public void setAuthor2(String author2) {
        mAuthor2 = author2;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getTrlLetterPair() {
        return mTrlLetterPair;
    }

    public void setTrlLetterPair(String trlLetterPair) {
        mTrlLetterPair = trlLetterPair;
    }

    public String getRandomBarCode() {
        return mRandomBarCode;
    }

    public void setRandomBarCode(String randomBarCode) {
        mRandomBarCode = randomBarCode;
    }

    public String getCopyRight() {
        return mCopyRight;
    }

    public void setCopyRight(String copyRight) {
        mCopyRight = copyRight;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public void setPublisher(String publisher) {
        mPublisher = publisher;
    }

    public String getISBN2() {
        return mISBN2;
    }

    public void setISBN2(String ISBN2) {
        mISBN2 = ISBN2;
    }

    public String getSharpOfCDs() {
        return mSharpOfCDs;
    }

    public void setSharpOfCDs(String sharpOfCDs) {
        mSharpOfCDs = sharpOfCDs;
    }

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String created) {
        mCreated = created;
    }

    public String getModified() {
        return mModified;
    }

    public void setModified(String modified) {
        mModified = modified;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(float rating) {
        mRating = rating;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getAvailQoh() {
        return mAvailQoh;
    }

    public void setAvailQoh(String availQoh) {
        mAvailQoh = availQoh;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public void setImage(List<String> image) {

        if (image != null && !image.isEmpty()) {
            setImage(image.get(0));
        }
    }


    public String getDescr() {
        return mDescr;
    }

    public void setDescr(String descr) {
        mDescr = descr;
    }

    public String getLiveMediaFee() {
        return mLiveMediaFee;
    }

    public void setLiveMediaFee(String liveMediaFee) {
        mLiveMediaFee = liveMediaFee;
    }

    public String getWeight() {
        return mWeight;
    }

    public void setWeight(String weight) {
        mWeight = weight;
    }

    public String getPhysical() {
        return mPhysical;
    }

    public void setPhysical(String physical) {
        mPhysical = physical;
    }

    public String getAvailable() {
        return mAvailable;
    }

    public void setAvailable(String available) {
        mAvailable = available;
    }

    public void setAvailable(List<String> availables) {
        if (availables != null && !availables.isEmpty()) {
            setAvailable(availables.get(0));
        }
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getNarator() {
        return mNarator;
    }

    public void setNarator(String narator) {
        mNarator = narator;
    }

    public String getBisac1() {
        return mBisac1;
    }

    public void setBisac1(String bisac1) {
        mBisac1 = bisac1;
    }

    public String getBisac2() {
        return mBisac2;
    }

    public void setBisac2(String bisac2) {
        mBisac2 = bisac2;
    }

    public String getBisac3() {
        return mBisac3;
    }

    public void setBisac3(String bisac3) {
        mBisac3 = bisac3;
    }

    public String getISBN() {
        return mISBN;
    }

    public void setISBN(String ISBN) {
        mISBN = ISBN;
    }

    public String getAbridgement() {
        return mAbridgement;
    }

    public void setAbridgement(String abridgement) {
        mAbridgement = abridgement;
    }

    public String getPrimaryGenre() {
        return mPrimaryGenre;
    }

    public void setPrimaryGenre(String primaryGenre) {
        mPrimaryGenre = primaryGenre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mFieldSet);
        dest.writeString(mActive);
        dest.writeString(mAuthor);
        dest.writeString(mAuthor2);
        dest.writeString(mCode);
        dest.writeString(mTrlLetterPair);
        dest.writeString(mRandomBarCode);
        dest.writeString(mCopyRight);
        dest.writeString(mPublisher);
        dest.writeString(mISBN2);
        dest.writeString(mSharpOfCDs);
        dest.writeString(mCreated);
        dest.writeString(mModified);
        dest.writeFloat(mRating);
        dest.writeString(mStatus);
        dest.writeString(mAvailQoh);
        dest.writeString(mTitle);
        dest.writeString(mImage);
        dest.writeString(mDescr);
        dest.writeString(mLiveMediaFee);
        dest.writeString(mWeight);
        dest.writeString(mPhysical);
        dest.writeString(mAvailable);
        dest.writeString(mLanguage);
        dest.writeString(mNarator);
        dest.writeString(mBisac1);
        dest.writeString(mBisac2);
        dest.writeString(mBisac3);
        dest.writeString(mISBN);
        dest.writeString(mAbridgement);
        dest.writeString(mPrimaryGenre);
    }
}

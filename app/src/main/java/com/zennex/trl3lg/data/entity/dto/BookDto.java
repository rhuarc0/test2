package com.zennex.trl3lg.data.entity.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nikit on 02.08.2017.
 */

public class BookDto {

    @SerializedName("field_set")
    private String mFieldSet;
    @SerializedName("active")
    private String mActive;
    @SerializedName("text8")
    private String mAuthor;
    @SerializedName("text5")
    private String mAuthor2;
    @SerializedName("code")
    private String mCode;
    @SerializedName("text13")
    private String mTrlLetterPair;
    @SerializedName("text9")
    private String mRandomBarCode;
    @SerializedName("text10")
    private String mCopyRight;
    @SerializedName("text14")
    private String mPublisher;
    @SerializedName("text11")
    private String mISBN2;
    @SerializedName("text12")
    private String mSharpOfCDs;
    @SerializedName("id")
    private String mId;
    @SerializedName("created")
    private String mCreated;
    @SerializedName("modified")
    private String mModified;
    @SerializedName("rating")
    private float mRating;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("availQoh")
    private String mAvailQoh;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("image")
    private List<String> mImage;
    @SerializedName("descr")
    private String mDescr;
    @SerializedName("live_media_fee")
    private String mLiveMediaFee;
    @SerializedName("weight")
    private String mWeight;
    @SerializedName("Physical")
    private String mPhysical;
    @SerializedName("text7")
    private List<String> mAvailable;
    @SerializedName("text22")
    private String mLanguage;
    @SerializedName("text20")
    private String mNarator;
    @SerializedName("text24")
    private String mBisac1;
    @SerializedName("text25")
    private String mBisac2;
    @SerializedName("text26")
    private String mBisac3;
    @SerializedName("text4")
    private String mISBN;
    @SerializedName("text21")
    private String mAbridgement;

    @SerializedName("bill_date")
    private String billDate;
    @SerializedName("list_order")
    private String listOrder;
    @SerializedName("renew")
    private int renew;
    @SerializedName("associated_item")
    private String associatedItem;



    public String getAbridgement() {
        return mAbridgement;
    }

    public void setAbridgement(String abridgement) {
        mAbridgement = abridgement;
    }

    public String getNarator() {
        return mNarator;
    }

    public void setNarator(String narator) {
        mNarator = narator;
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

    public String getISBN() {
        return mISBN;
    }

    public void setISBN(String ISBN) {
        mISBN = ISBN;
    }

    public String getSharpOfCDs() {
        return mSharpOfCDs;
    }

    public void setSharpOfCDs(String sharpOfCDs) {
        mSharpOfCDs = sharpOfCDs;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
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

    public List<String> getImage() {
        return mImage;
    }

    public void setImage(List<String> image) {
        mImage = image;
    }

    public List<String> getAvailable() {
        return mAvailable;
    }

    public void setAvailable(List<String> available) {
        mAvailable = available;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getAuthor2() {
        return mAuthor2;
    }

    public void setAuthor2(String author2) {
        mAuthor2 = author2;
    }

    public String getISBN2() {
        return mISBN2;
    }

    public void setISBN2(String ISBN2) {
        mISBN2 = ISBN2;
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

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getListOrder() {
        return listOrder;
    }

    public void setListOrder(String listOrder) {
        this.listOrder = listOrder;
    }

    public int getRenew() {
        return renew;
    }

    public void setRenew(int renew) {
        this.renew = renew;
    }

    public String getAssociatedItem() {
        return associatedItem;
    }

    public void setAssociatedItem(String associatedItem) {
        this.associatedItem = associatedItem;
    }
}

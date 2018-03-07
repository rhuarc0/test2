package com.zennex.trl3lg.data.entity.rest.request;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit on 02.08.2017.
 */

public class FetchBookListRequest extends BaseRequest<FetchBookListRequest.Data> {

    public static FetchBookListRequest newInstance(String moduleId, Data data) {
        FetchBookListRequest request = new FetchBookListRequest();
        request.fillDefaultFields();
        request.setData(data);
        request.setModuleid(moduleId);
        request.setType("findRentalItems");
        return request;
    }

    public static class Data {

        @SerializedName("filters")
        private List<BookFilter> mFilters;
        @SerializedName("keywordSearch")
        private KeywordSearch mKeywordSearch;
        @SerializedName("start")
        private String mStartPosition;
        @SerializedName("count")
        private String mCountBooks;

        public List<BookFilter> getFilters() {
            return mFilters;
        }

        public void setFilters(List<BookFilter> filters) {
            mFilters = filters;
        }

        public KeywordSearch getKeywordSearch() {
            return mKeywordSearch;
        }

        public void setKeywordSearch(KeywordSearch keywordSearch) {
            mKeywordSearch = keywordSearch;
        }

        public String getStartPosition() {
            return mStartPosition;
        }

        public void setStartPosition(String startPosition) {
            mStartPosition = startPosition;
        }

        public String getCountBooks() {
            return mCountBooks;
        }

        public void setCountBooks(String countBooks) {
            mCountBooks = countBooks;
        }
    }


    public static class BookFilter {

        public static final String FIELD_GROUP_ID = "group_id";
        public static final String FIELD_SET = "field_set";
        public static final String FIELD_ACTIVE = "active";

        @SerializedName("field")
        public String mField;
        @SerializedName("value")
        public List<String> mValue;

        public BookFilter(String field, List<String> value) {
            mField = field;
            mValue = value;
        }

        public String getField() {
            return mField;
        }

        public void setField(String mField) {
            this.mField = mField;
        }

        public List<String> getValue() {
            if (mValue == null) mValue = new ArrayList<>();
            return mValue;
        }

        public void setValue(List<String> value) {
            mValue = value;
        }

        public void addValue(String value) {
            if (mValue == null) mValue = new ArrayList<>();
            mValue.add(value);
        }
    }


    public static class KeywordSearch {

        public static final String TYPE_AND = "and";
        public static final String TYPE_OR = "or";

        @SerializedName("keyword")
        private String mKeyword;
        @SerializedName("fields")
        private String mFields;
        @SerializedName("type")
        private String mType;
        @SerializedName("matchWholeWords")
        private String mMatchWholeWords;


        public String getKeyword() {
            return mKeyword;
        }

        public void setKeyword(String keyword) {
            mKeyword = keyword;
        }

        public String getFields() {
            return mFields;
        }

        public void setFields(String fields) {
            mFields = fields;
        }

        public String getType() {
            return mType;
        }

        public void setType(String type) {
            mType = type;
        }

        public String getMatchWholeWords() {
            return mMatchWholeWords;
        }

        public void setMatchWholeWords(String matchWholeWords) {
            mMatchWholeWords = matchWholeWords;
        }

    }
}


package com.zennex.trl3lg.data.entity.rest.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nikita on 12.06.17.
 */

public class GetFieldsForSignUpResponse extends BaseResponse<List<GetFieldsForSignUpResponse.DataMemberField>> {


    public static class DataMemberField {

        @SerializedName("active")
        private String mActive;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("alias")
        private String mAlias;
        @SerializedName("user_alias")
        private String mUserAlias;
        @SerializedName("type")
        private String mType;
        @SerializedName("default_value")
        private String mDefaultValue;
        @SerializedName("required")
        private String mRequired;
        @SerializedName("list_order")
        private String mListOrder;
        @SerializedName("enum_set")
        private List<String> mEnumSet;


        public String getActive() {
            return mActive;
        }

        public void setActive(String active) {
            mActive = active;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public String getAlias() {
            return mAlias;
        }

        public void setAlias(String alias) {
            mAlias = alias;
        }

        public String getUserAlias() {
            return mUserAlias;
        }

        public void setUserAlias(String userAlias) {
            mUserAlias = userAlias;
        }

        public String getType() {
            return mType;
        }

        public void setType(String type) {
            mType = type;
        }

        public String getDefaultValue() {
            return mDefaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            mDefaultValue = defaultValue;
        }

        public String getRequired() {
            return mRequired;
        }

        public void setRequired(String required) {
            mRequired = required;
        }

        public String getListOrder() {
            return mListOrder;
        }

        public void setListOrder(String listOrder) {
            mListOrder = listOrder;
        }

        public List<String> getEnumSet() {
            return mEnumSet;
        }

        public void setEnumSet(List<String> enumSet) {
            mEnumSet = enumSet;
        }





        public static DataMemberField clone(DataMemberField dataMemberFieldOriginal) {

            if (dataMemberFieldOriginal == null) return null;

            DataMemberField dataMemberField = new DataMemberField();
            dataMemberField.setTitle(dataMemberFieldOriginal.getTitle());
            dataMemberField.setType(dataMemberFieldOriginal.getType());
            dataMemberField.setActive(dataMemberFieldOriginal.getActive());
            dataMemberField.setAlias(dataMemberFieldOriginal.getAlias());
            dataMemberField.setDefaultValue(dataMemberFieldOriginal.mDefaultValue);
            dataMemberField.setEnumSet(dataMemberFieldOriginal.getEnumSet());
            dataMemberField.setListOrder(dataMemberFieldOriginal.getListOrder());
            dataMemberField.setRequired(dataMemberFieldOriginal.getRequired());
            dataMemberField.setUserAlias(dataMemberFieldOriginal.getUserAlias());
            return dataMemberField;
        }


    }

}

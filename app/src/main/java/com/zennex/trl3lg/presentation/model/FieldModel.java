package com.zennex.trl3lg.presentation.model;

import android.support.annotation.StringRes;

import java.util.List;

/**
 * Created by nikita on 24.06.17.
 */

public class FieldModel {

    public static final String TYPE_VARCHAR = "varchar";
    public static final String TYPE_CHECKBOX = "checkbox";
    public static final String TYPE_ENUM = "enum";
    public static final String TYPE_TEXT = "text";
    public static final String TYPE_INTEGER = "integer";
    public static final String TYPE_DECIMAL = "decimal";


    private String mUuid;
    private boolean mActive;
    private String mTitle;
    private String mAlias;
    private String mUserAlias;
    private String mType;
    private String mDefaultValue;
    private boolean mRequired;
    private String mListOrder;
    private List<String> mEnumSet;
    private String mValue;
    private boolean mErrorEnable;
    private boolean mEnable = true;

    @StringRes
    private int mErrorMessage;

    public boolean isEnable() {
        return mEnable;
    }

    public void setEnable(boolean enable) {
        mEnable = enable;
    }

    public boolean isErrorEnable() {
        return mErrorEnable;
    }

    public void setErrorEnable(boolean errorEnable) {
        mErrorEnable = errorEnable;
    }


    public int getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(int errorMessage) {
        mErrorMessage = errorMessage;
    }

    public String getUuid() {
        return mUuid;
    }

    public void setUuid(String uuid) {
        mUuid = uuid;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public boolean isActive() {
        return mActive;
    }

    public void setActive(boolean active) {
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


    public boolean isRequired() {
        return mRequired;
    }

    public void setRequired(boolean required) {
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

    @Override
    public String toString() {
        return "type = " +
                mType +
                "\n" +
                "title = " +
                mTitle +
                "\n" +
                "alias = " +
                mAlias +
                "\n" +
                "user alias = " +
                mUserAlias;
    }


    public boolean isTypeVarcharPassword() {
        return getType().equals(TYPE_VARCHAR) && getAlias().equals("text0");
    }

    public boolean isTypeVarcharText() {
        return getType().equals(TYPE_VARCHAR) && !getAlias().equals("text0");
    }

    public boolean isTypeCheckBox() {
        return getType().equals(TYPE_CHECKBOX);
    }

    public boolean isTypeSpinner() {
        return getType().equals(TYPE_ENUM);
    }

    public boolean isTypeText() {
        return getType().equals(TYPE_TEXT);
    }

    public boolean isTypeInteger() {
        return getType().equals(TYPE_INTEGER);
    }

    public boolean isTypeDecimal() {
        return getType().equals(TYPE_DECIMAL);
    }


}

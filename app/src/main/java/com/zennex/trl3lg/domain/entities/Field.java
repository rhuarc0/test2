package com.zennex.trl3lg.domain.entities;

import java.util.List;

public class Field {

    private String uuid;
    private boolean active;
    private String title;
    private String alias;
    private String userAlias;
    private String type;
    private String defaultValue;
    private boolean required;
    private String listOrder;
    private List<String> enumSet;
    private String value;
    private boolean errorEnable;
    private boolean enable = true;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getListOrder() {
        return listOrder;
    }

    public void setListOrder(String listOrder) {
        this.listOrder = listOrder;
    }

    public List<String> getEnumSet() {
        return enumSet;
    }

    public void setEnumSet(List<String> enumSet) {
        this.enumSet = enumSet;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isErrorEnable() {
        return errorEnable;
    }

    public void setErrorEnable(boolean errorEnable) {
        this.errorEnable = errorEnable;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}

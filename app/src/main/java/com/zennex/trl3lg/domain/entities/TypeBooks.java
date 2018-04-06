package com.zennex.trl3lg.domain.entities;

public enum TypeBooks {
    All(""),
    Cd("4"),
    AudioBook("11");

    private String value;

    TypeBooks(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


package com.sl.student.primary.model;

public enum Datatype {
    STR("String"),
    INT("Integer"),
    DOU("Double"),
    BOOL("Boolean");

    private String dataType;

    Datatype(String dataType) {
        this.dataType = dataType;
    }

    public String getString() {
        return dataType;
    }
}

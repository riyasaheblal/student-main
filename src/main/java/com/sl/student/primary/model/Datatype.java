package com.sl.student.primary.model;

public enum Datatype {
    STR(String.class),
    INT(Integer.class),
    DOU(Double.class);

    private Class<?> clazz;

    Datatype(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}

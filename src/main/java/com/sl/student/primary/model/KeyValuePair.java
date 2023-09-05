package com.sl.student.primary.model;

import lombok.Builder;

@Builder
public record KeyValuePair(String key, String value,Datatype dataType) {
}

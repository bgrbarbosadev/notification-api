package br.com.bgrbarbosa.notification_api.model.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    SCHEDULED,
    SENT,
    CANCELLED;

    @JsonValue
    public String getValue() {
        return this.name();
    }

    @JsonCreator
    public static Status fromValue(String value) {
        return Status.valueOf(value.toUpperCase());
    }
}

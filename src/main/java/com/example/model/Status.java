package com.example.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Status {

    PENDING("Pending"),
    ACTIVE("Active"),
    DONE("Done");

    private String taskStatus;

    Status(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public static Status getFromName(String name) {
        Optional<Status> statusOptional = Arrays
                .stream(values())
                .filter(x -> x.getTaskStatus().equalsIgnoreCase(name))
                .findFirst();
        if (statusOptional.isPresent()) {
            return statusOptional.get();
        } else {
            throw new UnsupportedOperationException("Unsupported gender: " + name);
        }
    }
}

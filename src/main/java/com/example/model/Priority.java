package com.example.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Priority {
    CRITICAL("Critical"),
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private String taskPriority;

    Priority(String taskPriority){
        this.taskPriority = taskPriority;
    }

    public static Priority getFromName(String name) {
        Optional<Priority> optionalPriority = Arrays
                .stream(values())
                .filter(x -> x.getTaskPriority().equalsIgnoreCase(name))
                .findFirst();
        if (optionalPriority.isPresent()) {
            return optionalPriority.get();
        } else {
            throw new UnsupportedOperationException("Unsupported gender: " + name);
        }
    }

}

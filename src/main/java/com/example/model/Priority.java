package com.example.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Priority {

    CRITICAL("critical"),
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low"),
    NONE("none");

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
            throw new UnsupportedOperationException("Unsupported priority: " + name);
        }
    }

    public static Boolean isPriority(String name) {
        Optional<Priority> priority = Arrays
                .stream(values())
                .filter(x -> x.getTaskPriority().equalsIgnoreCase(name))
                .findFirst();
        return priority.isPresent();
    }

}

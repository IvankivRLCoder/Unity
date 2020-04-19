package com.example.utils;

import com.example.dto.pagination.PaginationDto;
import com.example.error.PaginationException;

import java.util.List;

public class PaginationUtils {

    public final static int PAGE_SIZE = 20;

    public static <T> PaginationDto<T> paginate(List<T> entities, int pageNumber) {
        if (pageNumber <= 0) {
            throw new PaginationException("Page or size can't be less or equals to 0");
        }

        int start = (pageNumber - 1) * PAGE_SIZE;

        int end = start + PAGE_SIZE;

        int quantity = entities.size();
        int entityLeft = Math.max(quantity - (pageNumber * PAGE_SIZE), 0);

        if (start < quantity && end > quantity) {
            end = quantity;
        }

        if (start > quantity || end > quantity) {
            throw new PaginationException("There is no such quantity of entities");
        }
        entities = entities.subList(start, end);
        return PaginationDto.<T>builder()
                .entities(entities)
                .quantity(quantity)
                .entitiesLeft(entityLeft)
                .build();
    }

}

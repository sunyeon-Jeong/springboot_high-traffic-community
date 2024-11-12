package com.mallang.hightrafficcommunity.dto;

import lombok.*;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    public enum SortStatus {
        CATEGORIES, NEWEST, OLDEST
    }

    // CATEGORY Table column
    private int id;
    private String category;

    // business logic data
    private SortStatus sortStatus;
    private int searchCount;
    private int pagingStartOffset;

}
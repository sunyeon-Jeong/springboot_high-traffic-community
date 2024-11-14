package com.mallang.hightrafficcommunity.dto.request;

import com.mallang.hightrafficcommunity.dto.CategoryDTO;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostSearchRequestDTO {

    private int id;
    private String title;
    private String contents;
    private int userId;
    private int categoryId;
    private CategoryDTO.SortStatus sortStatus;

}
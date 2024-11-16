package com.mallang.hightrafficcommunity.dto;

import lombok.*;

@Builder
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    // COMMENT Table Column
    private int id;
    private String contents;

    // FK
    private int postId;
    private int subCommentId;

}
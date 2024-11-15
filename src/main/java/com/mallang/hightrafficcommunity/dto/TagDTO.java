package com.mallang.hightrafficcommunity.dto;

import lombok.*;

@Builder
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {

    // TAG Table column
    private int id;
    private String tag;
    private String url;
    private int postId;

}
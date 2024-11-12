package com.mallang.hightrafficcommunity.dto;

import lombok.*;

import java.util.Date;

@Builder
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    // POST Table column
    private int id;
    private String title;
    private int isAdmin;
    private String contents;
    private int views;
    private int fileId;
    private Date createTime;
    private Date modifyTime;

    // FK
    private int userId;
    private int categoryId;

}
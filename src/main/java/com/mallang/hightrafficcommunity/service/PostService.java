package com.mallang.hightrafficcommunity.service;

import com.mallang.hightrafficcommunity.dto.PostDTO;

import java.util.List;

public interface PostService {

    // 게시글 등록
    void createPost(String username, PostDTO postDTO);

}
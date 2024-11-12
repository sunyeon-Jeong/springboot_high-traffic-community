package com.mallang.hightrafficcommunity.service;

import com.mallang.hightrafficcommunity.dto.PostDTO;

import java.util.List;

public interface PostService {

    // 게시글 등록
    void createPost(String username, PostDTO postDTO);

    // 사용자 게시글 조회
    List<PostDTO> getUserPosts(int userId);

    // 게시글 수정
    void updatePost(PostDTO updatePostDTO);

}
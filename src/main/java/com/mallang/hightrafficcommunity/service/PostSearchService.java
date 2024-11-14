package com.mallang.hightrafficcommunity.service;

import com.mallang.hightrafficcommunity.dto.PostDTO;
import com.mallang.hightrafficcommunity.dto.request.PostSearchRequestDTO;

import java.util.List;

public interface PostSearchService {

    // 게시글 검색
    List<PostDTO> getSearchPosts(PostSearchRequestDTO postSearchRequestDTO);

}
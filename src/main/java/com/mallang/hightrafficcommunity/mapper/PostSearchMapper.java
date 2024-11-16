package com.mallang.hightrafficcommunity.mapper;

import com.mallang.hightrafficcommunity.dto.PostDTO;
import com.mallang.hightrafficcommunity.dto.request.PostSearchRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostSearchMapper {

    // 게시글 검색
    List<PostDTO> getSearchPosts(PostSearchRequestDTO postSearchRequestDTO);

    // 해시태그 검색
    List<PostDTO> getSearchPostsByTag(String tag);

}
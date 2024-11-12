package com.mallang.hightrafficcommunity.mapper;

import com.mallang.hightrafficcommunity.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

    // 게시글 등록
    int createPost(PostDTO postDTO);

}

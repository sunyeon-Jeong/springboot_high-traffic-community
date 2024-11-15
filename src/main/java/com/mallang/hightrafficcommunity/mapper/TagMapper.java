package com.mallang.hightrafficcommunity.mapper;

import com.mallang.hightrafficcommunity.dto.TagDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {

    // 태그 등록
    int createTag(TagDTO tagDTO);

    // 태그 수정
    void updateTag(TagDTO tagDTO);

    // PostTag 테이블 생성
    void createPostTag(Integer tagId, Integer postId);

}
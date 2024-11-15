package com.mallang.hightrafficcommunity.mapper;

import com.mallang.hightrafficcommunity.dto.TagDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {

    // 태그 등록
    int createTag(TagDTO tagDTO);

}
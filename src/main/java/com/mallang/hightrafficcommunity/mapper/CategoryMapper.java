package com.mallang.hightrafficcommunity.mapper;

import com.mallang.hightrafficcommunity.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CategoryMapper {

    // 카테고리 등록
    int createCategory(CategoryDTO categoryDTO);

    // 카테고리 수정
    void updateCategory(CategoryDTO categoryDTO);

    // 카테고리 삭제
    void deleteCategory(@Param("id") int id);

}
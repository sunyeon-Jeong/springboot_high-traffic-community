package com.mallang.hightrafficcommunity.service;

import com.mallang.hightrafficcommunity.dto.CategoryDTO;

public interface CategoryService {

    //  카테고리 등록
    void createCategory(String username, CategoryDTO categoryDTO);

    // 카테고리 수정
    void updateCategory(CategoryDTO categoryDTO);

    // 카테고리 삭제
    void deleteCategory(int id);

}
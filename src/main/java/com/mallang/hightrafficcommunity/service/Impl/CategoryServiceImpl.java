package com.mallang.hightrafficcommunity.service.Impl;

import com.mallang.hightrafficcommunity.dto.CategoryDTO;
import com.mallang.hightrafficcommunity.exception.CommonException;
import com.mallang.hightrafficcommunity.mapper.CategoryMapper;
import com.mallang.hightrafficcommunity.mapper.UserMapper;
import com.mallang.hightrafficcommunity.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CategoryServiceImpl implements CategoryService {

    /* Mapper 연결 */
    @Autowired
    private CategoryMapper categoryMapper;

    /* 카테고리 등록 */
    @Override
    public void createCategory(String username, CategoryDTO categoryDTO) {

        if (username != null) {

            try {
                categoryMapper.createCategory(categoryDTO);
            } catch(RuntimeException exception) {
                log.error("createCategory ERROR");
                throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
            }

        } else {
            log.error("create Category ERROR! {}", categoryDTO);
            throw new RuntimeException("create Category ERROR! 카테고리 등록 메서드를 확인해주세요\n" + "Params : " + categoryDTO);
        }

    }

    /* 카테고리 수정 */
    @Override
    public void updateCategory(CategoryDTO categoryDTO) {

        if (categoryDTO != null && categoryDTO.getCategory() != null) {

            try {
                categoryMapper.updateCategory(categoryDTO);
            } catch(RuntimeException exception) {
                log.error("updateCategory ERROR");
                throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
            }

        } else {
            log.error("update Category ERROR! {}", categoryDTO);
            throw new RuntimeException("update Category ERROR! 카테고리 수정 메서드를 확인해주세요\n" + "Params : " + categoryDTO);
        }

    }

    /* 카테고리 삭제 */
    @Override
    public void deleteCategory(int id) {

        if (id != 0) {

            try {
                categoryMapper.deleteCategory(id);
            } catch(RuntimeException exception) {
                log.error("deleteCategory ERROR");
                throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
            }

        } else {
            log.error("deleteCategory ERROR! {}", id);
            throw new RuntimeException("deleteCategory ERROR! 카테고리 삭제 메서드를 확인해주세요\n" + "Params : " + id);
        }

    }

}
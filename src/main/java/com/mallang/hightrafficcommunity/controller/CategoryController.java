package com.mallang.hightrafficcommunity.controller;

import com.mallang.hightrafficcommunity.aop.LoginCheck;
import com.mallang.hightrafficcommunity.dto.CategoryDTO;
import com.mallang.hightrafficcommunity.service.Impl.CategoryServiceImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Log4j2
public class CategoryController {

    private CategoryServiceImpl categoryServiceImpl;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    /* 카테고리 등록 */
    @PostMapping("create-category")
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(userType = LoginCheck.UserType.ADMIN)
    public void createCategory(String username, @RequestBody CategoryDTO categoryDTO) {

        categoryServiceImpl.createCategory(username, categoryDTO);

    }

    /* 카테고리 수정 */
    @PatchMapping("{id}")
    @LoginCheck(userType = LoginCheck.UserType.ADMIN)
    public void updateCategory(String username,
                                                @PathVariable(name = "id") int id,
                                                @RequestBody CategoryRequest categoryRequest) {

        CategoryDTO updateCategory = new CategoryDTO(id, categoryRequest.getCategory(),
                                                                                    CategoryDTO.SortStatus.NEWEST, 10, 1);

        categoryServiceImpl.updateCategory(updateCategory);

    }

    /* 카테고리 삭제 */
    @DeleteMapping("{id}")
    @LoginCheck(userType = LoginCheck.UserType.ADMIN)
    public void deleteCategory(String username,
                                                @PathVariable(name = "id") int id) {

        categoryServiceImpl.deleteCategory(id);

    }

    /* -----CategoryRequest----- */
    // CategoryController에서만 사용될 DTO이므로 외부 재사용 X -> 클래스 내부에 선언
    @Getter
    @Setter
    private static class CategoryRequest {
        private int id;
        private String category;
    }

}
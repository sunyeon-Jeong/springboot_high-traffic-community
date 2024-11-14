package com.mallang.hightrafficcommunity.controller;

import com.mallang.hightrafficcommunity.dto.PostDTO;
import com.mallang.hightrafficcommunity.dto.request.PostSearchRequestDTO;
import com.mallang.hightrafficcommunity.service.Impl.PostSearchServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@Log4j2
@RequiredArgsConstructor
public class PostSearchController {

    private final PostSearchServiceImpl postsearchServiceImpl;

    /* 게시글 검색 */
    // 검색 조건이 많을 경우, GET이 아닌 POST 사용
    @PostMapping
    public PostSearchResponseDTO getSearchPosts(@RequestBody PostSearchRequestDTO postSearchRequestDTO) {

        List<PostDTO> postDTOList = postsearchServiceImpl.getSearchPosts(postSearchRequestDTO);

        return new PostSearchResponseDTO(postDTOList);

    }

    /* -----PostSearchResponseDTO----- */
    @Getter
    @AllArgsConstructor
    private static class PostSearchResponseDTO {
        private List<PostDTO> postDTOList;
    }

}
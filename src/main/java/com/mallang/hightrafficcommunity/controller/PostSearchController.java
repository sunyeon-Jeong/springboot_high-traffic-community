package com.mallang.hightrafficcommunity.controller;

import com.mallang.hightrafficcommunity.dto.PostDTO;
import com.mallang.hightrafficcommunity.dto.request.PostSearchRequestDTO;
import com.mallang.hightrafficcommunity.service.Impl.PostSearchServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

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

    /* 해시태그 검색 */
    @GetMapping
    public PostSearchResponseDTO getSearchPostsByTag(String tag) {

        List<PostDTO> postDTOList = postsearchServiceImpl.getSearchPostsByTag(tag);
        log.info(tag + "컨트롤러");

        return new PostSearchResponseDTO(postDTOList);

    }

    /* -----PostSearchResponseDTO----- */
    @Getter
    @AllArgsConstructor
    private static class PostSearchResponseDTO {
        private List<PostDTO> postDTOList;
    }

}
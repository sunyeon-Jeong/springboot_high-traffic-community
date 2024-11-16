package com.mallang.hightrafficcommunity.service.Impl;

import com.mallang.hightrafficcommunity.dto.PostDTO;
import com.mallang.hightrafficcommunity.dto.request.PostSearchRequestDTO;
import com.mallang.hightrafficcommunity.mapper.PostSearchMapper;
import com.mallang.hightrafficcommunity.service.PostSearchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PostSearchServiceImpl implements PostSearchService {

    @Autowired
    private PostSearchMapper postSearchMapper;

    /* 게시글 검색 */
    @Async
    @Cacheable(value = "getSearchPosts", key = "'getSearchPosts' + #postSearchRequestDTO.getTitle() + #postSearchRequestDTO.getCategoryId()")
    @Override
    public List<PostDTO> getSearchPosts(PostSearchRequestDTO postSearchRequestDTO) {

        List<PostDTO> postDtoList = null;

        try {
            postDtoList = postSearchMapper.getSearchPosts(postSearchRequestDTO);
        } catch (RuntimeException exception){
            log.error("getSearchPosts ERROR! {}", exception.getMessage());
        }

        return postDtoList;

    }

    /* 해시태그 검색 */
    @Override
    public List<PostDTO> getSearchPostsByTag(String tag) {

        List<PostDTO> postDTOList = null;

        try {
            postDTOList = postSearchMapper.getSearchPostsByTag(tag);
            log.info(tag);
        } catch (RuntimeException exception) {
            log.error("getSearchPostsByTag ERROR! {}", tag);
        }

        return postDTOList;

    }

}
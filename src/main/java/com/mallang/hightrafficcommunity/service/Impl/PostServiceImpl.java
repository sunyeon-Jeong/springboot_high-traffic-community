package com.mallang.hightrafficcommunity.service.Impl;

import com.mallang.hightrafficcommunity.dto.PostDTO;
import com.mallang.hightrafficcommunity.dto.UserDTO;
import com.mallang.hightrafficcommunity.mapper.PostMapper;
import com.mallang.hightrafficcommunity.mapper.UserMapper;
import com.mallang.hightrafficcommunity.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Log4j2
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    /* 게시글 등록 */
    @Override
    public void createPost(String username, PostDTO postDTO) {

        UserDTO userInfo = userMapper.getUserInfo(username);

        postDTO.setUserId(userInfo.getId());
        postDTO.setCreateTime(new Date());

        if (userInfo != null) {
            postMapper.createPost(postDTO);
        } else {
            log.error("createPost ERROR! {}", postDTO);
            throw new RuntimeException("createPost ERROR! 게시글 등록 메서드를 확인해주세요\n" + "Params : " + postDTO);
        }

    }

}
package com.mallang.hightrafficcommunity.controller;

import com.mallang.hightrafficcommunity.aop.LoginCheck;
import com.mallang.hightrafficcommunity.dto.PostDTO;
import com.mallang.hightrafficcommunity.dto.UserDTO;
import com.mallang.hightrafficcommunity.dto.response.CommonResponse;
import com.mallang.hightrafficcommunity.service.Impl.PostServiceImpl;
import com.mallang.hightrafficcommunity.service.Impl.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@Log4j2
public class PostController {

    private final UserServiceImpl userServiceImpl;
    private final PostServiceImpl postServiceImpl;

    public PostController(PostServiceImpl postServiceImpl, UserServiceImpl userServiceImpl) {
        this.postServiceImpl = postServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    /* 게시글 등록 */
    @PostMapping("create-post")
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(userType = LoginCheck.UserType.MEMBER)
    public ResponseEntity<CommonResponse<PostDTO>> createPost(String username,
                                                                                                             @RequestBody PostDTO postDTO) {

        postServiceImpl.createPost(username, postDTO);

        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "createPost", postDTO);
        return ResponseEntity.ok(commonResponse);

    }

    /* 사용자 게시글 조회 */
    @GetMapping("user-posts")
    @LoginCheck(userType = LoginCheck.UserType.MEMBER)
    public ResponseEntity<CommonResponse<PostDTO>> getUserPosts(String username) {

        UserDTO userInfo = userServiceImpl.getUserInfo(username);
        List<PostDTO> postDtoList = postServiceImpl.getUserPosts(userInfo.getId());

        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "getUserPosts", postDtoList);
        return ResponseEntity.ok(commonResponse);

    }

}
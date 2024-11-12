package com.mallang.hightrafficcommunity.mapper;

import com.mallang.hightrafficcommunity.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    // 게시글 등록
    int createPost(PostDTO postDTO);

    // 사용자 게시글 조회
    List<PostDTO> getUserPosts(int userId);

    // 게시글 수정
    void updatePost(PostDTO updatePostDTO);

    // 게시글 삭제
    void deletePost(int id);

}

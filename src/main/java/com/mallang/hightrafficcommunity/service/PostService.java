package com.mallang.hightrafficcommunity.service;

import com.mallang.hightrafficcommunity.dto.CommentDTO;
import com.mallang.hightrafficcommunity.dto.PostDTO;
import com.mallang.hightrafficcommunity.dto.TagDTO;

import java.util.List;

public interface PostService {

    // 게시글 등록
    void createPost(String username, PostDTO postDTO);

    // 사용자 게시글 조회
    List<PostDTO> getUserPosts(int userId);

    // 게시글 수정
    void updatePost(PostDTO updatePostDTO);

    // 게시글 삭제
    void deletePost(int userId, int id);

    /* -----Comments----- */
    // 게시글 댓글 등록
    void createComment(CommentDTO commentDTO);

    // 게시글 댓글 수정
    void updateComment(CommentDTO commentDTO);

    // 게시글 댓글 삭제
    void deleteComment(int userId, int id);

    /* -----Tags----- */
    // 태그 등록
    void createTag(TagDTO tagDTO);

    // 태그 수정
    void updateTag(TagDTO tagDTO);

    // 태그 삭제
    void deleteTag(int userId, int tagId);

}
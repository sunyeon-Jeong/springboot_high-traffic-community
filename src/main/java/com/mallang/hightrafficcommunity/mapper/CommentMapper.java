package com.mallang.hightrafficcommunity.mapper;

import com.mallang.hightrafficcommunity.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    // 게시글 댓글 등록
    int createComment(CommentDTO commentDTO);

    // 게시글 댓글 수정
    void updateComment(CommentDTO commentDTO);

    // 게시글 댓글 삭제
    void deleteComment(int id);

}
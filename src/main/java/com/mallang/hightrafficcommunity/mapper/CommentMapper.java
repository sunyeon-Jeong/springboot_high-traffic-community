package com.mallang.hightrafficcommunity.mapper;

import com.mallang.hightrafficcommunity.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    // 게시글 댓글 등록
    int createComment(CommentDTO commentDTO);

}
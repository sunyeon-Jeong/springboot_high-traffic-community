package com.mallang.hightrafficcommunity.service.Impl;

import com.mallang.hightrafficcommunity.dto.CommentDTO;
import com.mallang.hightrafficcommunity.dto.PostDTO;
import com.mallang.hightrafficcommunity.dto.TagDTO;
import com.mallang.hightrafficcommunity.dto.UserDTO;
import com.mallang.hightrafficcommunity.mapper.CommentMapper;
import com.mallang.hightrafficcommunity.mapper.PostMapper;
import com.mallang.hightrafficcommunity.mapper.TagMapper;
import com.mallang.hightrafficcommunity.mapper.UserMapper;
import com.mallang.hightrafficcommunity.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private TagMapper tagMapper;

    /* 게시글 등록 */
    // 데이터 일관성 유지를 위해 게시글 등록 -> Redis 캐시 삭제 -> 다음 getSearchPosts 메서드 실행 시 최신 캐시 저장
    @CacheEvict(value = "getSearchPosts", allEntries = true)
    @Override
    public void createPost(String username, PostDTO postDTO) {

        UserDTO userInfo = userMapper.getUserInfo(username);

        postDTO.setUserId(userInfo.getId());
        postDTO.setCreateTime(new Date());

        if (userInfo != null) {
            postMapper.createPost(postDTO);

            if (postDTO.getTagDtoList() != null) {

                // PostTag 테이블 생성
                for (int i = 0; i < postDTO.getTagDtoList().size(); i++) {
                    TagDTO tagDTO = postDTO.getTagDtoList().get(i);
                    tagMapper.createTag(tagDTO);
                    // M:N 관계 테이블 생성
                    tagMapper.createPostTag(tagDTO.getId(), postDTO.getId());
                }

            }

        } else {
            log.error("createPost ERROR! {}", postDTO);
            throw new RuntimeException("createPost ERROR! 게시글 등록 메서드를 확인해주세요\n" + "Params : " + postDTO);
        }

    }

    /* 사용자 게시글 조회 */
    @Override
    public List<PostDTO> getUserPosts(int userId) {

        List<PostDTO> postDtoList = postMapper.getUserPosts(userId);
        return postDtoList;

    }

    /* 게시글 수정 */
    @Override
    public void updatePost(PostDTO updatePostDTO) {

        if (updatePostDTO != null && updatePostDTO.getId() != 0 && updatePostDTO.getUserId() != 0) {
            postMapper.updatePost(updatePostDTO);
        } else {
            log.error("updatePost ERROR! {}", updatePostDTO);
            throw new RuntimeException("updatePost ERROR! 게시글 수정 메서드를 확인해주세요\n" + "Params : " + updatePostDTO);
        }

    }

    /* 게시글 삭제 */
    @Override
    public void deletePost(int userId, int id) {

        if (userId != 0 && id != 0) {
            postMapper.deletePost(id);
        } else {
            log.error("deletePost ERROR! {}", id);
            throw new RuntimeException("deletePost ERROR! 게시글 삭제 메서드를 확인해주세요\n" + "Params : " + id);
        }

    }

    /* -----Comments----- */
    /* 게시글 댓글 등록 */
    @Override
    public void createComment(CommentDTO commentDTO) {

        if (commentDTO.getPostId() != 0) {
            commentMapper.createComment(commentDTO);
        } else {
            log.error("createComment ERROR! {}", commentDTO);
            throw new RuntimeException("createComment ERROR! 게시글 댓글 등록 메서드를 확인해주세요\n" + "Params : " + commentDTO);
        }

    }

    /* 게시글 댓글 수정 */
    @Override
    public void updateComment(CommentDTO commentDTO) {

        if (commentDTO != null) {
            commentMapper.updateComment(commentDTO);
        } else {
            log.error("updateComment ERROR! {}", commentDTO);
            throw new RuntimeException("updateComment ERROR! 게시글 댓글 수정 메서드를 확인해주세요\n" + "Params : " + commentDTO);
        }

    }

    /* 게시글 댓글 삭제 */
    @Override
    public void deleteComment(int userId, int id) {

        if (userId != 0 && id != 0) {
            commentMapper.deleteComment(id);
        } else {
            log.error("deleteComment ERROR! {}", id);
            throw new RuntimeException("deleteComment ERROR! 게시글 댓글 삭제 메서드를 확인해주세요\n" + "Params : " + id);
        }

    }

    /* -----Tags----- */
    /* 태그 등록 */
    @Override
    public void createTag(TagDTO tagDTO) {

        if (tagDTO.getPostId() != 0) {
            tagMapper.createTag(tagDTO);
        } else {
            log.error("createTag ERROR! {}", tagDTO);
            throw new RuntimeException("createTag ERROR! 태그 등록 메서드를 확인해주세요\n" + "Params : " + tagDTO);
        }

    }

    /* 태그 수정 */
    @Override
    public void updateTag(TagDTO tagDTO) {

        if (tagDTO != null) {
            tagMapper.updateTag(tagDTO);
        } else {
            log.error("updateTag ERROR! {}", tagDTO);
            throw new RuntimeException("updateTag ERROR! 태그 수정 메서드를 확인해주세요\n" + "Params : " + tagDTO);
        }

    }

    /* 태그 삭제 */
    @Override
    public void deleteTag(int userId, int tagId) {

        if (userId != 0 && tagId != 0) {
            tagMapper.deleteTag(tagId);
        } else {
            log.error("deleteTag ERROR! {}", tagId);
            throw new RuntimeException("deleteTag ERROR! 태그 삭제 메서드를 확인해주세요\n" + "Params : " + tagId);
        }

    }

}
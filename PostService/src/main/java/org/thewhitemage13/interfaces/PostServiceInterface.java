package org.thewhitemage13.interfaces;

import org.thewhitemage13.dto.OpenPostDTO;
import org.thewhitemage13.dto.PostDTO;
import org.thewhitemage13.dto.UpdatePostDTO;
import org.thewhitemage13.exception.PostNotFoundException;

import java.util.List;

public interface PostServiceInterface {
    boolean postVerification(Long postId);
    void createPost(PostDTO postDTO);
    void updatePost(Long userId, UpdatePostDTO updatePostDTO) throws PostNotFoundException;
    void deletePost(Long userId) throws PostNotFoundException;
    List<PostDTO> getPostsByUserId(Long userId) throws PostNotFoundException;
    List<PostDTO> getAllPosts();
    Long getUserIdByPostId(Long postId);
    void deleteAllByUserId(Long userId) throws PostNotFoundException;
    List<String> getUrlsByUserId(Long userId);
    Long getCountPostByUserId(Long userId);
    List<OpenPostDTO> openAllPostsByUserId(Long userId);
    OpenPostDTO openPost(Long postId) throws PostNotFoundException;
}

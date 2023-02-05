package az.company.blog.service;

import az.company.blog.dto.request.ReqPost;
import az.company.blog.dto.response.BaseResponse;

public interface PostService {
    BaseResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    BaseResponse addPost(ReqPost reqPost, Long userId, Long categoryId);

    BaseResponse getPostById(Long postId);

    BaseResponse getPostsByUser(Long userId);

    BaseResponse getPostsByCategory(Long categoryId);

    BaseResponse updatePost(ReqPost reqPost, Long postId);
}

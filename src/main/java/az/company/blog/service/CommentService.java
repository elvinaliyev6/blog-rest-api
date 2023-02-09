package az.company.blog.service;

import az.company.blog.dto.request.ReqComment;
import az.company.blog.dto.response.BaseResponse;

public interface CommentService {
    BaseResponse addComment(ReqComment reqComment, Long postId);

    BaseResponse getCommentsByPostId(Long postId);

    BaseResponse deleteComment(Long postId, Long id);

    BaseResponse updateComment(Long postId, Long id, ReqComment reqComment);

    BaseResponse getCommentById(Long postId, Long id);
}

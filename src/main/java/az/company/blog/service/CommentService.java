package az.company.blog.service;

import az.company.blog.dto.request.ReqComment;
import az.company.blog.dto.response.BaseResponse;

public interface CommentService {
    BaseResponse addComment(ReqComment reqComment, Long postId);

    BaseResponse getCommentsByPostId(Long postId);
}

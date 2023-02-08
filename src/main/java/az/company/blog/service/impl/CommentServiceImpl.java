package az.company.blog.service.impl;

import az.company.blog.dto.request.ReqComment;
import az.company.blog.dto.response.BaseResponse;
import az.company.blog.dto.response.RespComment;
import az.company.blog.dto.response.RespStatus;
import az.company.blog.entity.Comment;
import az.company.blog.entity.Post;
import az.company.blog.enums.EnumAvailableStatus;
import az.company.blog.enums.ErrorCodeEnum;
import az.company.blog.exception.BaseException;
import az.company.blog.repository.CommentRepository;
import az.company.blog.repository.PostRepository;
import az.company.blog.service.CommentService;
import az.company.blog.util.DTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final DTOConverter dtoConverter;
    private final PostRepository postRepository;

    @Override
    public BaseResponse addComment(ReqComment reqComment, Long postId) {
        Post post = postRepository.findByPostIdAndActive(postId, EnumAvailableStatus.ACTIVE.getValue())
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.POST_NOT_FOUND.getCode(), ErrorCodeEnum.POST_NOT_FOUND.getMessage()));
        Comment comment = dtoConverter.commentDTOToComment(reqComment);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        RespComment respComment = dtoConverter.commentToCommentDTO(savedComment);

        return BaseResponse.builder()
                .data(respComment)
                .status(RespStatus.getSuccessStatus())
                .build();
    }

    @Override
    public BaseResponse getCommentsByPostId(Long postId) {
        return null;
    }
}

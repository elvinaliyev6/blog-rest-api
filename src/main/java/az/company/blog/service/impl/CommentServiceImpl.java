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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final DTOConverter dtoConverter;
    private final PostRepository postRepository;

    @Override
    public BaseResponse addComment(ReqComment reqComment, Long postId) {
        Post post = fetchPostIfExist(postId);
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

        Post post = fetchPostIfExist(postId);
        List<RespComment> commentList = commentRepository
                .findAllByActiveAndPost(EnumAvailableStatus.ACTIVE.getValue(), post)
                .stream()
                .map(comment -> dtoConverter.commentToCommentDTO(comment))
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(commentList)
                .status(RespStatus.getSuccessStatus())
                .build();
    }

    @Override
    public BaseResponse deleteComment(Long postId, Long id) {
        Post post=fetchPostIfExist(postId);
        Comment comment=fetchCommentIfExist(id);

        if (!comment.getPost().getPostId().equals(post.getPostId()))
            throw new BaseException(ErrorCodeEnum.COMMENT_NOT_POST);

        comment.setActive(EnumAvailableStatus.DEACTIVE.getValue());
       Comment deletedComment= commentRepository.save(comment);
        return BaseResponse.builder()
                .data(dtoConverter.commentToCommentDTO(deletedComment))
                .status(RespStatus.getSuccessStatus())
                .build();
    }

    @Override
    public BaseResponse updateComment(Long postId, Long id, ReqComment reqComment) {
        Post post=fetchPostIfExist(postId);
        Comment comment=fetchCommentIfExist(id);

        if (!comment.getPost().getPostId().equals(post.getPostId()))
            throw new BaseException(ErrorCodeEnum.COMMENT_NOT_POST);

        comment.setName(reqComment.getName());
        comment.setBody(reqComment.getBody());
        comment.setEmail(reqComment.getEmail());
        Comment updatedComment=commentRepository.save(comment);

        return BaseResponse.builder()
                .data(dtoConverter.commentToCommentDTO(updatedComment))
                .status(RespStatus.getSuccessStatus())
                .build();
    }

    @Override
    public BaseResponse getCommentById(Long postId, Long id) {
        Post post = fetchPostIfExist(postId);
        Comment comment = fetchCommentIfExist(id);

        if (!comment.getPost().getPostId().equals(post.getPostId()))
            throw new BaseException(ErrorCodeEnum.COMMENT_NOT_POST);

        return BaseResponse.builder()
                .data(dtoConverter.commentToCommentDTO(comment))
                .status(RespStatus.getSuccessStatus())
                .build();
    }

    private Post fetchPostIfExist(Long id) {
        return postRepository.findByPostIdAndActive(id, EnumAvailableStatus.ACTIVE.getValue())
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.POST_NOT_FOUND));
    }

    private Comment fetchCommentIfExist(Long id) {
        return commentRepository.findByIdAndActive(id, EnumAvailableStatus.ACTIVE.getValue())
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.COMMENT_NOT_FOUND));
    }
}

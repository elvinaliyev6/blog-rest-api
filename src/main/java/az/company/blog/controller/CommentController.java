package az.company.blog.controller;

import az.company.blog.dto.request.ReqComment;
import az.company.blog.dto.response.BaseResponse;
import az.company.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments/{postId}")
    public BaseResponse addComment(
            @RequestBody @Valid ReqComment reqComment,
            @PathVariable Long postId){
        return commentService.addComment(reqComment,postId);
    }

    @GetMapping("/posts/{postId}/comments")
    public BaseResponse getCommentsByPostId(@PathVariable Long postId){
        return commentService.getCommentsByPostId(postId);
    }

}
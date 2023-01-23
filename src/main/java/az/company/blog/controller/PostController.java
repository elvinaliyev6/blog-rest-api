package az.company.blog.controller;

import az.company.blog.config.AppConstraints;
import az.company.blog.dto.request.ReqPost;
import az.company.blog.dto.response.BaseResponse;
import az.company.blog.entity.Post;
import az.company.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    public final PostService postService;

    @GetMapping("")
    public BaseResponse getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstraints.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstraints.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstraints.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstraints.SORT_DIR, required = false) String sortDir
    ){
        return postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
    }

    @PostMapping("")
    public BaseResponse addPost(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "categoryId") Long categoryId,
            @RequestBody @Valid ReqPost reqPost
            ){
        return postService.addPost(reqPost,userId,categoryId);
    }

}

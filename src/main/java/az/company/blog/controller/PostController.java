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

    @GetMapping("/{postId}")
    public BaseResponse getPostById(@PathVariable(value = "postId") Long postId){
        return postService.getPostById(postId);
    }

    @GetMapping("/user/{userId}")
    public BaseResponse getPostsByUser(@PathVariable(value = "userId") Long userId) {
        return postService.getPostsByUser(userId);
    }

    @GetMapping("/category/{categoryId}")
    public BaseResponse getPostsByCategory(@PathVariable(value = "categoryId") Long categoryId){
        return postService.getPostsByCategory(categoryId);
    }

    @PutMapping("/{postId}")
    public BaseResponse updatePost(@RequestBody @Valid ReqPost reqPost,@PathVariable("postId") Long postId){
        return postService.updatePost(reqPost,postId);
    }

}
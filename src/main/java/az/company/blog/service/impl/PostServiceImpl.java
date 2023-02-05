package az.company.blog.service.impl;

import az.company.blog.dto.request.ReqPost;
import az.company.blog.dto.response.BaseResponse;
import az.company.blog.dto.response.RespPost;
import az.company.blog.dto.response.RespStatus;
import az.company.blog.dto.response.RespUser;
import az.company.blog.entity.Category;
import az.company.blog.entity.Post;
import az.company.blog.entity.User;
import az.company.blog.enums.EnumAvailableStatus;
import az.company.blog.enums.ErrorCodeEnum;
import az.company.blog.exception.BaseException;
import az.company.blog.repository.CategoryRepository;
import az.company.blog.repository.PostRepository;
import az.company.blog.repository.UserRepository;
import az.company.blog.service.PostService;
import az.company.blog.util.DTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final DTOConverter converter;

    @Override
    public BaseResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort;
        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> page = postRepository.findAllByActive(pageable, EnumAvailableStatus.ACTIVE.getValue());

        List<Post> posts = page.getContent();
        List<RespPost> postList = new ArrayList<>();
        for (Post post : posts) {
            RespPost respPost = converter.postToPostDTO(post);
            respPost.setLastPage(page.isLast());
            respPost.setPageNumber(page.getNumber());
            respPost.setPageSize(page.getSize());
            respPost.setTotalPages(page.getTotalPages());
            respPost.setTotalElements(page.getTotalElements());
            postList.add(respPost);
        }
        if (postList.isEmpty() || postList == null) {
            throw new BaseException(ErrorCodeEnum.POST_NOT_FOUND);
        }
        BaseResponse response = new BaseResponse();
        response.setData(postList);
        response.setStatus(RespStatus.getSuccessStatus());
        return response;
    }

    @Override
    public BaseResponse addPost(ReqPost reqPost, Long userId, Long categoryId) {
       User user= userRepository
                .findByIdAndActive(userId,EnumAvailableStatus.ACTIVE.getValue())
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.USER_NOT_FOUND));
        Category category=categoryRepository.findByIdAndActive(categoryId,EnumAvailableStatus.ACTIVE.getValue())
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.CATEGORY_NOT_FOUND));

        Post post=converter.postDTOToPost(reqPost);
        post.setCategory(category);
        post.setUser(user);
        postRepository.save(post);
//        RespPost respPost=converter.postToPostDTO(post);

        return BaseResponse.builder()
                .data(converter.postToPostDTO(post))
                .status(RespStatus.getSuccessStatus())
                .build();
    }

    @Override
    public BaseResponse getPostById(Long postId) {
        Post post = postRepository.findByPostIdAndActive(postId, EnumAvailableStatus.ACTIVE.getValue())
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.POST_NOT_FOUND));
        RespPost respPost = converter.postToPostDTO(post);
        BaseResponse baseResponse = BaseResponse.builder()
                .data(respPost)
                .status(RespStatus.getSuccessStatus())
                .build();
        return baseResponse;
    }

    @Override
    public BaseResponse getPostsByUser(Long userId) {
        User user = userRepository.findByIdAndActive(userId, EnumAvailableStatus.ACTIVE.getValue())
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.USER_NOT_FOUND));
        List<RespPost> posts = postRepository
                .findByUserAndActive(user, EnumAvailableStatus.ACTIVE.getValue())
                .stream().map(converter::postToPostDTO)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(posts)
                .status(RespStatus.getSuccessStatus())
                .build();
    }

    @Override
    public BaseResponse getPostsByCategory(Long categoryId) {
        Category category=categoryRepository.findByIdAndActive(categoryId,EnumAvailableStatus.ACTIVE.getValue())
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.CATEGORY_NOT_FOUND));

        List<RespPost> posts=postRepository
                .findByCategoryAndActive(category,EnumAvailableStatus.ACTIVE.getValue())
                .stream().map(converter::postToPostDTO)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(posts)
                .status(RespStatus.getSuccessStatus())
                .build();
    }

    @Override
    public BaseResponse updatePost(ReqPost reqPost, Long postId) {
        Post post=postRepository.findByPostIdAndActive(postId,EnumAvailableStatus.ACTIVE.getValue())
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.POST_NOT_FOUND));
            User user=userRepository.findByIdAndActive(reqPost.getUserId(), EnumAvailableStatus.ACTIVE.getValue())
                    .orElseThrow(() -> new BaseException(ErrorCodeEnum.USER_NOT_FOUND));
            Category category=categoryRepository.findByIdAndActive(reqPost.getCategoryId(), EnumAvailableStatus.ACTIVE.getValue())
                    .orElseThrow(() -> new BaseException(ErrorCodeEnum.CATEGORY_NOT_FOUND));
            post.setUser(user);
            post.setCategory(category);
            post.setContent(reqPost.getContent());
            post.setTitle(reqPost.getTitle());

            RespPost respPost=converter.postToPostDTO(post);
            return BaseResponse
                    .builder().data(respPost)
                    .status(RespStatus.getSuccessStatus())
                    .build();
    }
}

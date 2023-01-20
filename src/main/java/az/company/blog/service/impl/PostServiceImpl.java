package az.company.blog.service.impl;

import az.company.blog.dto.response.BaseResponse;
import az.company.blog.dto.response.RespPost;
import az.company.blog.dto.response.RespStatus;
import az.company.blog.entity.Post;
import az.company.blog.enums.EnumAvailableStatus;
import az.company.blog.enums.ErrorCodeEnum;
import az.company.blog.exception.BaseException;
import az.company.blog.repository.PostRepository;
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

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final DTOConverter converter;

    @Override
    public BaseResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort;
        if (sortBy.equalsIgnoreCase("asc")) {
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
}

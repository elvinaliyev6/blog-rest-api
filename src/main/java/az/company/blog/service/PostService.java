package az.company.blog.service;

import az.company.blog.dto.response.BaseResponse;

public interface PostService {
    BaseResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
}

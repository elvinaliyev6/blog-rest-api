package az.company.blog.service;

import az.company.blog.dto.request.ReqCategory;
import az.company.blog.dto.response.BaseResponse;
import az.company.blog.dto.response.RespCategory;

import java.util.List;

public interface CategoryService {
    BaseResponse addCategory(ReqCategory reqCategory);

    BaseResponse updateCategory(ReqCategory reqCategory,Long categoryId);

    BaseResponse getAllCategories();

    BaseResponse getCategoryById(Long categoryId);

    BaseResponse deleteCategory(Long categoryId);
}

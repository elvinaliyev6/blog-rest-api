package az.company.blog.service.impl;

import az.company.blog.dto.request.ReqCategory;
import az.company.blog.dto.response.BaseResponse;
import az.company.blog.dto.response.RespCategory;
import az.company.blog.dto.response.RespStatus;
import az.company.blog.entity.Category;
import az.company.blog.enums.EnumAvailableStatus;
import az.company.blog.enums.ErrorCodeEnum;
import az.company.blog.exception.BaseException;
import az.company.blog.repository.CategoryRepository;
import az.company.blog.service.CategoryService;
import az.company.blog.util.DTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final DTOConverter converter;

    @Override
    public BaseResponse addCategory(ReqCategory reqCategory) {
        Category savedCategory = categoryRepository.save(converter.categoryDTOToCategory(reqCategory));
        System.out.println(savedCategory);
        RespCategory respCategory = converter.categoryToCategoryDTO(savedCategory);
        BaseResponse response=BaseResponse.builder()
                .data(respCategory)
                .status(RespStatus.getSuccessStatus())
                .build();
        return response;
    }

    @Override
    public BaseResponse updateCategory(ReqCategory reqCategory, Long categoryId) {
        Category existedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.CATEGORY_NOT_FOUND));

        existedCategory.setName(reqCategory.getName());
        existedCategory.setDescription(reqCategory.getDescription());
        Category updatedCategory = categoryRepository.save(existedCategory);

        RespCategory respCategory=converter.categoryToCategoryDTO(updatedCategory);
        BaseResponse response=BaseResponse.builder()
                .data(respCategory)
                .status(RespStatus.getSuccessStatus())
                .build();

        return response;
    }

    @Override
    public BaseResponse getAllCategories() {
        List<RespCategory> categories = categoryRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue())
                .stream().map(converter::categoryToCategoryDTO)
                .collect(Collectors.toList());
        if (categories.isEmpty() || categories == null)
            throw new BaseException(ErrorCodeEnum.CATEGORY_NOT_FOUND);
        BaseResponse response = BaseResponse.builder()
                .data(categories)
                .status(RespStatus.getSuccessStatus())
                .build();
        return response;
    }

    @Override
    public BaseResponse getCategoryById(Long categoryId) {
        Category existingCategory = categoryRepository.findByIdAndActive(categoryId, EnumAvailableStatus.ACTIVE.getValue())
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.CATEGORY_NOT_FOUND));

        RespCategory respCategory = converter.categoryToCategoryDTO(existingCategory);
        BaseResponse response = BaseResponse
                .builder()
                .data(respCategory)
                .status(RespStatus.getSuccessStatus())
                .build();
        return response;
    }

    @Override
    public BaseResponse deleteCategory(Long categoryId) {
        Category existingCategory = categoryRepository
                .findByIdAndActive(categoryId, EnumAvailableStatus.ACTIVE.getValue())
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.CATEGORY_NOT_FOUND));
        existingCategory.setActive(EnumAvailableStatus.DEACTIVE.getValue());
        categoryRepository.save(existingCategory);
        RespCategory respCategory = converter.categoryToCategoryDTO(existingCategory);
        BaseResponse response = BaseResponse.builder()
                .data(respCategory)
                .status(RespStatus.getSuccessStatus())
                .build();
        return response;
    }
}

package az.company.blog.service.impl;

import az.company.blog.dto.request.ReqCategory;
import az.company.blog.dto.response.BaseResponse;
import az.company.blog.dto.response.RespCategory;
import az.company.blog.dto.response.RespStatus;
import az.company.blog.entity.Category;
import az.company.blog.enums.EnumAvailableStatus;
import az.company.blog.repository.CategoryRepository;
import az.company.blog.service.CategoryService;
import az.company.blog.util.DTOConverter;
import com.fasterxml.jackson.databind.ser.Serializers;
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
                .orElseThrow(() -> new RuntimeException("category id exception"));

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
        List<RespCategory> categories = categoryRepository.findAll()
                .stream().map(converter::categoryToCategoryDTO)
                .collect(Collectors.toList());
        BaseResponse response=BaseResponse.builder()
                .data(categories)
                .status(RespStatus.getSuccessStatus())
                .build();
        return response;
    }

    @Override
    public BaseResponse getCategoryById(Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Customer did not find"));

        RespCategory respCategory= converter.categoryToCategoryDTO(existingCategory);
        BaseResponse response=BaseResponse
                .builder()
                .data(respCategory)
                .status(RespStatus.getSuccessStatus())
                .build();
        return response;
    }

    @Override
    public BaseResponse deleteCategory(Long categoryId) {
        Category existingCategory = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Customer did not find"));
        existingCategory.setActive(EnumAvailableStatus.ACTIVE.getValue());
        RespCategory respCategory=converter.categoryToCategoryDTO(existingCategory);
        BaseResponse response=BaseResponse.builder()
                .data(respCategory)
                .status(RespStatus.getSuccessStatus())
                .build();
        return response;
    }
}

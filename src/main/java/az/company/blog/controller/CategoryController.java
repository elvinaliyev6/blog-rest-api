package az.company.blog.controller;

import az.company.blog.dto.request.ReqCategory;
import az.company.blog.dto.response.BaseResponse;
import az.company.blog.dto.response.RespCategory;
import az.company.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public BaseResponse getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public BaseResponse getCategoryById(@PathVariable(name = "categoryId") Long categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping("")
    public BaseResponse addCategory(@RequestBody @Valid ReqCategory reqCategory) {
        return categoryService.addCategory(reqCategory);
    }

    @PutMapping("/{categoryId}")
    public BaseResponse updateCategory(@RequestBody @Valid ReqCategory reqCategory,
                                                       @PathVariable(name = "categoryId") Long categoryId) {
        return categoryService.updateCategory(reqCategory,categoryId);
    }

    @PutMapping("/delete/{categoryId}")
    public BaseResponse deleteCategory(@PathVariable(name = "categoryId") Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }
}

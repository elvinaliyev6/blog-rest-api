package az.company.blog.controller;

import az.company.blog.dto.request.ReqCategory;
import az.company.blog.dto.response.RespCategory;
import az.company.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<RespCategory>> getAllCategories(){
        List<RespCategory> categories=categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<RespCategory> getCategoryById(@PathVariable(name = "categoryId") Long categoryId){
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @PostMapping("")
    public ResponseEntity<RespCategory> addCategory(@RequestBody @Valid ReqCategory reqCategory) {
        RespCategory createdCategory = categoryService.addCategory(reqCategory);
        return ResponseEntity.ok(createdCategory);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<RespCategory> updateCategory(@RequestBody @Valid ReqCategory reqCategory,
                                                       @PathVariable(name = "categoryId") Long categoryId) {
        RespCategory updatedCategory = categoryService.updateCategory(reqCategory, categoryId);
        return ResponseEntity.ok(updatedCategory);
    }

    @PutMapping("/delete/{categoryId}")
    public ResponseEntity<RespCategory> deleteCategory(@PathVariable(name = "categoryId") Long categoryId){
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }
}

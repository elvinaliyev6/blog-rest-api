package az.company.blog.util;

import az.company.blog.dto.request.ReqCategory;
import az.company.blog.dto.response.RespCategory;
import az.company.blog.entity.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DTOConverter {
    private final ModelMapper modelMapper;

    public RespCategory categoryToCategoryDTO(Category category){
        return modelMapper.map(category,RespCategory.class);
    }

    public Category categoryDTOToCategory(ReqCategory reqCategory){
        return modelMapper.map(reqCategory,Category.class);
    }


}

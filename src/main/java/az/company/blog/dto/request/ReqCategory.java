package az.company.blog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder
public class ReqCategory {

    @NotEmpty
    @Size(min = 4, message = "Minimum size of category name must be 4 characters")
    private String name;

    @Size(min = 15,message = "Minimum size of category must be 4 characters")
    private String description;

}

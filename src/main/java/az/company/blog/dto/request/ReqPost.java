package az.company.blog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqPost {

    @NotBlank
    @Size(min = 3, message = "Minimum size of title name must be 3 characters")
    private String title;

    @NotBlank
    @Size(min = 10, message = "Minimum size of post content 10 characters")
    private String content;
}

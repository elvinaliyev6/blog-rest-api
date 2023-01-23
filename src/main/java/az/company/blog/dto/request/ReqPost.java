package az.company.blog.dto.request;

import az.company.blog.dto.response.RespCategory;
import az.company.blog.dto.response.RespComment;
import az.company.blog.dto.response.RespUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqPost {

    @NotEmpty
    @Size(min = 3, message = "Minimum size of title name must be 3 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Minimum size of post content 10 characters")
    private String content;
    private String imageName;
    private RespCategory category;
    private RespUser user;
    private Set<RespComment> comments = new HashSet<>();
}

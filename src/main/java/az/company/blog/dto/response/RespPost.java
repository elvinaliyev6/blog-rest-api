package az.company.blog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespPost {
    private Long postId;
    private String title;
    private String content;
    private String imageName;
    private RespCategory category;
    private RespUser user;
    private Set<RespComment> comments = new HashSet<>();
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean lastPage;
}

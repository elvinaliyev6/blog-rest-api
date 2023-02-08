package az.company.blog.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RespPost {
    private Long postId;
    private String title;
    private String content;
    private RespCategory category;
    private RespUser user;
    private Set<RespComment> comments = new HashSet<>();
}

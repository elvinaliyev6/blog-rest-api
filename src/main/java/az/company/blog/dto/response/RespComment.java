package az.company.blog.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RespComment {
    private Long id;
    private String name;
    private String email;
    private String body;
}

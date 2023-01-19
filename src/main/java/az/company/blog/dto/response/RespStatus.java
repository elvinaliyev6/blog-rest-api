package az.company.blog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespStatus {

    private int code;
    private String message;

    public static RespStatus getSuccessStatus() {
        return new RespStatus(1, "process success compiled!");
    }

}

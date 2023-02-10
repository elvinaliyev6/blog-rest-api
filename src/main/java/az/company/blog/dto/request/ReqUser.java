package az.company.blog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqUser {
    @NotEmpty
    @Size(min = 4,message = "Name must contain at least 4 characters")
    private String name;

    @Email(message = "Email address is not valid!")
    private String email;
    @NotEmpty
    @Size(min = 4,max = 12,message = "Password must contain at least 4 and maximum 12 characters!")
    private String password;
}

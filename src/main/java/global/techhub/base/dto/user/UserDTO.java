package global.techhub.base.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class UserDTO {

    String id;

    @NotNull
    @NotEmpty
    String username;

    String password;

    String phone;

    @NotNull
    @Email
    String email;
}

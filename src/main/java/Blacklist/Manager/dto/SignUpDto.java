package Blacklist.Manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    @javax.validation.constraints.Pattern(regexp = "(\\w+@)(\\w+\\.com)", message = "wrong email format")
    private String  email;

    @NotEmpty(message = "password should not be blank")
    private String password;

    @NotEmpty(message = "confirm password should not be blank")
    private String confirmPassword;

    @NotEmpty(message = "role should not be blank")
    private String role;

}



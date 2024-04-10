package Blacklist.Manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @javax.validation.constraints.Pattern(regexp = "(\\w+@)(\\w+\\.com)", message = "wrong email format")
    private String  email;
    @javax.validation.constraints.Pattern(regexp = "(ROLE_BLACKLIST_ADMIN|ROLE_USER_ADMIN)",
            message = "set role to ROLE_BLACKLIST_ADMIN or ROLE_USER_ADMIN")
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

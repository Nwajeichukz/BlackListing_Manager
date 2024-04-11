package Blacklist.Manager.controller;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.UserDto;
import Blacklist.Manager.entity.User;
import Blacklist.Manager.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_USER_ADMIN')")
    @PostMapping("/create_user")
    public ResponseEntity<AppResponse<String>> createUser(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.CreateUser(userDto));
    }


    @PreAuthorize("hasRole('ROLE_USER_ADMIN')")
    @GetMapping("/all_users")
    public ResponseEntity<AppResponse<Page<UserDto>>> getPaginatedAllUsers(@PageableDefault(size = 10) Pageable pageable) {
        AppResponse<Page<UserDto>> response = userService.getPaginatedAllUsers(pageable);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ROLE_USER_ADMIN')")
    @PutMapping("/users/{id}")
    public ResponseEntity<AppResponse<UserDto>> updateUser(@PathVariable(value = "id") Long userId,
                                            @Valid @RequestBody UserDto userDto) {

            return ResponseEntity.ok(userService.updateUser(userId, userDto));
    }

    
    @PreAuthorize("hasRole('ROLE_USER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AppResponse<String>> deleteUser(@PathVariable Long id) {
        AppResponse<String> response = userService.deleteUser(id);
        return ResponseEntity.ok(response);
    }

}

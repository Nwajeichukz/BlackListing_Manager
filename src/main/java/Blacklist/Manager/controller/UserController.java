package Blacklist.Manager.controller;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.UserDto;
import Blacklist.Manager.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_USER_ADMIN')")
    @PostMapping("/create_user")
    public ResponseEntity<AppResponse<String>> createUser(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.CreateUser(userDto));
    }

}

package Blacklist.Manager.controller;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.AuthenticationRequest;
import Blacklist.Manager.dto.SignUpDto;
import Blacklist.Manager.service.authentication.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AppResponse<Map<String, Object>>> createUser(@Valid @RequestBody SignUpDto request){
        return ResponseEntity.ok(authService.createUser(request));
    }


    @PostMapping("/login")
    public ResponseEntity<AppResponse<String>> login(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authService.login(authenticationRequest));
    }

}

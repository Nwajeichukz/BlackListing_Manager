package Blacklist.Manager.controller;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.AuthenticationRequest;
import Blacklist.Manager.dto.CreatePasswordDto;
import Blacklist.Manager.exception.ApiException;
import Blacklist.Manager.service.authentication.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/update")
    public ResponseEntity<AppResponse<Map<String, Object>>> createUser(@Valid @RequestBody CreatePasswordDto request){
        return ResponseEntity.ok(authService.createPassword(request));
    }


    @PostMapping("/login")
    public ResponseEntity<AppResponse<String>> login(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authService.login(authenticationRequest));
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<AppResponse<String>> handleEmailNotFound(ApiException ex) {
        return new ResponseEntity<>(new AppResponse<>(1, ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}

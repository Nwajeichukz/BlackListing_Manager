package Blacklist.Manager.service.authentication;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.AuthenticationRequest;
import Blacklist.Manager.dto.SignUpDto;

import java.util.Map;

public interface AuthService {
    AppResponse<Map<String, Object>> createUser(SignUpDto request);

    AppResponse<String> login(AuthenticationRequest authenticationRequest);

}

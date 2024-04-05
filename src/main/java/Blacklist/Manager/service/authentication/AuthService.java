package Blacklist.Manager.service.authentication;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.AuthenticationRequest;
import Blacklist.Manager.dto.CreatePasswordDto;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface AuthService {
    AppResponse<Map<String, Object>> createPassword(CreatePasswordDto request);

    AppResponse<String> login(AuthenticationRequest authenticationRequest);

}

package Blacklist.Manager.service.authentication;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.AuthenticationRequest;
import Blacklist.Manager.dto.CreatePasswordDto;
import Blacklist.Manager.entity.User;
import Blacklist.Manager.exception.ApiException;
import Blacklist.Manager.repository.UserRepository;
import Blacklist.Manager.service.JwtService;
import Blacklist.Manager.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final MyUserDetailsService myUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public AppResponse<Map<String, Object>> createPassword(CreatePasswordDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ApiException("email not found"));

        if (user.getPassword() == null) user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return  new AppResponse<>(0,"Password Successfully Created", Map.of(
                "id", user.getId(),
                "email", user.getEmail()
        ));
    }


    @Override
    public AppResponse<String> login(AuthenticationRequest authenticationRequest) {
        User checkUser = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new ApiException("email not found"));

        if (checkUser.getPassword() == null) return new AppResponse<>(0, "password not found, set password to enable login");

        var user = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword()))
            return  new AppResponse<>(0,"wrong gmail/password");

        var jwtToken = jwtService.generateToken(user);

        return  new AppResponse<>(0,"Successfully logged in", jwtToken);

    }
}
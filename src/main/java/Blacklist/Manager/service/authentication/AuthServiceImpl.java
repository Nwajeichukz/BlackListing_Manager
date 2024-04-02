package Blacklist.Manager.service.authentication;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.AuthenticationRequest;
import Blacklist.Manager.dto.SignUpDto;
import Blacklist.Manager.entity.Role;
import Blacklist.Manager.entity.User;
import Blacklist.Manager.exception.ApiException;
import Blacklist.Manager.repository.RoleRepository;
import Blacklist.Manager.repository.UserRepository;
import Blacklist.Manager.service.JwtService;
import Blacklist.Manager.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final MyUserDetailsService myUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;


    public AppResponse<Map<String, Object>> createUser(SignUpDto request) {
        boolean checkIfUserExist = userRepository.existsByEmail(request.getEmail());

        if (checkIfUserExist) throw new ApiException("User already exists, login to continue");

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (!request.getPassword().equals(request.getConfirmPassword()))
            return new AppResponse<>(-1, "passwords do not correspond");

        Role roles = roleRepository.findByName(request.getRole().toUpperCase())
                .orElseThrow(() -> new ApiException("Role not found"));

        user.setRoles(roles);

        userRepository.save(user);

        return  new AppResponse<>(0,"User Successfully Saved", Map.of(
                "id", user.getId(),
                "email", user.getEmail()
        ));
    }


    public AppResponse<String> login(AuthenticationRequest authenticationRequest) {

        var user = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        log.info(user.getPassword());

        if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword()))
            return  new AppResponse<>(0,"wrong gmail/password");

        var jwtToken = jwtService.generateToken(user);

        return  new AppResponse<>(0,"Successfully logged in", jwtToken);

    }
}
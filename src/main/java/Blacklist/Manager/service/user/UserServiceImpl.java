package Blacklist.Manager.service.user;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.CreatePasswordDto;
import Blacklist.Manager.dto.UserDto;
import Blacklist.Manager.entity.Role;
import Blacklist.Manager.entity.User;
import Blacklist.Manager.repository.RoleRepository;
import Blacklist.Manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public AppResponse<String> CreateUser(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());

        String userDtoRole = userDto.getRole();

        Role roles = roleRepository.findByName(userDtoRole);

        if (roles == null) {
            Role newRole = new Role();
            newRole.setName(userDtoRole);
            user.setRoles(newRole);
        }else{
            user.setRoles(roles);
        }

        userRepository.save(user);

        return new AppResponse<>(0, "user created successfully");
    }
}
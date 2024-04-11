package Blacklist.Manager.service.user;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.CreatePasswordDto;
import Blacklist.Manager.dto.UserDto;
import Blacklist.Manager.entity.Role;
import Blacklist.Manager.entity.User;
import Blacklist.Manager.exception.ApiException;
import Blacklist.Manager.repository.RoleRepository;
import Blacklist.Manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
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


    @Override
    public AppResponse<Page<UserDto>> getPaginatedAllUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return new AppResponse<>(0, "Success", userPage.map(this::convertToDto));
    }

    @Override
    public AppResponse<UserDto> updateUser(Long id, UserDto userDto){
        User user = userRepository.findById(id).orElseThrow(() -> new ApiException("Id Not found"));

        user.setEmail(userDto.getEmail());
        
        Role role = roleRepository.findByName(userDto.getRole());
        user.setRoles(role);

        final User updatedUser = userRepository.save(user);
        return new AppResponse<>(0, "User updated successfully",  convertToDto(updatedUser));
       
    }

    @Override
    public AppResponse<String> deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }

        userRepository.deleteById(id);

        return new AppResponse<String>(0, "User deleted successfully");
    }



    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        if (user.getRoles() != null) {
            dto.setRole(user.getRoles().getName());
        }
        return dto;
    }
}
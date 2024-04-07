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

import java.util.List;
import java.util.stream.Collectors;

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
    public AppResponse<List<UserDto>> getAllUsers() {
        List<User> users = userRepository.findAll(); 
        List<UserDto> userDtos = users.stream().map(this::convertToDto).collect(Collectors.toList());
    
        return new AppResponse<>(0, "Users fetched successfully", userDtos);
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail()); // Set the email from User entity
        if (user.getRoles() != null) {
            dto.setRole(user.getRoles().getName()); // Set the role name from Role entity
        }
        return dto;
    }


    @Override
    public AppResponse<Page<UserDto>> getPaginatedAllUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
       // return userPage.map(this::convertToDto);
        return new AppResponse<>(0, "Success", userPage.map(this::convertToDto));
    }

    @Override
    public AppResponse<UserDto> updateUser(Long id, UserDto userDto) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found for this id :: " + id));

        user.setEmail(userDto.getEmail());
        
        Role role = roleRepository.findByName(userDto.getRole());
                // .orElseThrow(() -> new Exception("Role not found: " + userDto.getRole()));
        user.setRoles(role);

        final User updatedUser = userRepository.save(user);
        return new AppResponse<>(0, "User updated successfully",  convertToDto(updatedUser));
       
    }

    @Override
    public AppResponse<String> deleteUser(Long id) {
        return userRepository.findById(id)
            .map(user -> {
                userRepository.delete(user);
                return new AppResponse<String>(0, "User deleted successfully");
            })
            .orElseGet(() -> new AppResponse<String>(1, "User not found with id: " + id));
    }

    // @Override
    // public void deleteUser(Long id) {
    //     userRepository.deleteById(id);
    // }


    // @Override
    // public List<User> getAllUsers() {
    //     return userRepository.findAll();
    // }

    
}
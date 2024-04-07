package Blacklist.Manager.service.user;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.UserDto;
//import Blacklist.Manager.entity.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface UserService {
    AppResponse<String> CreateUser(UserDto userDto);
    AppResponse<List<UserDto>> getAllUsers();
    AppResponse<Page<UserDto>> getPaginatedAllUsers(Pageable pageable);
    AppResponse<UserDto> updateUser(Long id, UserDto userDto) throws Exception;
    AppResponse<String> deleteUser(Long id);
    //void deleteUser(Long id);
    //List<User> getAllUsers();
}

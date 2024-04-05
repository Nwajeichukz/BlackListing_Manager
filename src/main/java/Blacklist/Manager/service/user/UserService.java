package Blacklist.Manager.service.user;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.UserDto;
import org.springframework.stereotype.Repository;


@Repository
public interface UserService {
    AppResponse<String> CreateUser(UserDto userDto);

}

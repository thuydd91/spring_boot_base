package global.techhub.base.service.user;

import global.techhub.base.dto.user.UserDTO;
import global.techhub.base.dto.user.UserUpdatePasswordCommandDTO;

public interface UserService {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserDTO register(UserDTO userDTO);

    UserDTO updateUser(String userId, UserDTO userDTO);

    UserDTO getUserById(String userId);

    boolean updatePassword(String username, UserUpdatePasswordCommandDTO userUpdatePasswordCommandDTO);
}

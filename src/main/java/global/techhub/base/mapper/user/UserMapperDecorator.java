package global.techhub.base.mapper.user;

import global.techhub.base.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import global.techhub.base.dto.user.UserDTO;

public abstract class UserMapperDecorator implements UserMapper {

    @Autowired
    @Qualifier("delegate")
    private UserMapper delegate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User toUser(UserDTO userDTO) {
        User user = delegate.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }
}

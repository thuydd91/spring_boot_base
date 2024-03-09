package global.techhub.base.service.user;

import com.google.common.collect.ImmutableSet;
import global.techhub.base.entity.user.Role;
import global.techhub.base.entity.user.User;
import global.techhub.base.event.publisher.UserRegisterPublisher;
import global.techhub.base.exception.NotFoundException;
import global.techhub.base.mapper.user.UserMapper;
import global.techhub.base.repository.user.RoleRepository;
import global.techhub.base.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import global.techhub.base.dto.user.UserDTO;
import global.techhub.base.dto.user.UserUpdatePasswordCommandDTO;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final UserRegisterPublisher userRegisterPublisher;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO register(UserDTO userDTO) {

        User user = userMapper.toUser(userDTO);
        user.setId(UUID.randomUUID().toString());
        user.setRoles(getDefaultRole());

        User savedUser = userRepository.save(user);
        UserDTO savedUserDTO = userMapper.toUserDTO(savedUser);

        userRegisterPublisher.publishUserRegisterEvent(savedUserDTO);

        return savedUserDTO;
    }

    @Override
    public UserDTO updateUser(String userId, UserDTO userDTO) throws NotFoundException {

        return userRepository.findById(userId)
                .map((User user) -> {
                    user.setPhone(userDTO.getPhone());
                    user.setEmail(userDTO.getEmail());
                    return userMapper.toUserDTO(userRepository.save(user));
                })
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public UserDTO getUserById(String userId) throws NotFoundException {

        return userRepository.findById(userId)
                .map(userMapper::toUserDTO)
                .orElseThrow(NotFoundException::new);
    }

    private Set<Role> getDefaultRole() {
        Role role = roleRepository.findUserRole();
        return ImmutableSet.of(role);
    }

    @Override
    public boolean existsByUsername(String username) {

        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {

        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean updatePassword(String username, UserUpdatePasswordCommandDTO userUpdatePasswordCommandDTO) {

        return userRepository.findByUsername(username).map(user -> {
            if (passwordEncoder.matches(userUpdatePasswordCommandDTO.oldPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(userUpdatePasswordCommandDTO.password()));
                userRepository.save(user);
                return true;
            }

            return false;
        }).orElse(false);
    }
}

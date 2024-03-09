package global.techhub.base.mapper.user;

import global.techhub.base.dto.user.UserDTO;
import global.techhub.base.entity.user.User;
import global.techhub.base.mapper.aggregator.UserDTOAggregator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import global.techhub.base.mapper.aggregator.UserAggregator;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @DisplayName("Test UserDTO to User")
    @ParameterizedTest
    @CsvSource({
            "john.doe1, password, john.doe1@yopmail.com, 12345678",
            "john.doe2, password, john.doe1@yopmail.com, 12345678",
    })
    public void testUserDTOtoUser(@AggregateWith(UserDTOAggregator.class) UserDTO userDTO) {

        //When
        User user = userMapper.toUser(userDTO);

        //Then
        assertNotEquals(userDTO.getPassword(), user.getPassword());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getPhone(), user.getPhone());
    }

    @DisplayName("Test User to UserDTO")
    @ParameterizedTest
    @CsvSource({
            "john.doe1, password, john.doe1@yopmail.com, 12345678",
            "john.doe2, password, john.doe2@yopmail.com, 1737180381",
    })
    public void testUserToUserDTO(@AggregateWith(UserAggregator.class) User user) {

        //When
        UserDTO userDTO = userMapper.toUserDTO(user);

        //Then
        assertNull(userDTO.getPassword());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getPhone(), user.getPhone());
    }

}
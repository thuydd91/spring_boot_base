package global.techhub.base.mapper.user;

import global.techhub.base.entity.user.User;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import global.techhub.base.dto.user.UserDTO;

@Mapper
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);
}

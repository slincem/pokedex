package com.lincz.pokedex.web.mappers;

import com.lincz.pokedex.domain.User;
import com.lincz.pokedex.web.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}

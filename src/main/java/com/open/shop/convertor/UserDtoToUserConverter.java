package com.open.shop.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.UserDto;
import com.open.shop.model.db.User;

@Service
public class UserDtoToUserConverter implements Converter<UserDto, User> {

  public User convert(@NonNull UserDto userDto) {
    return new User(
        userDto.id(),
        userDto.firstName(),
        userDto.lastName(),
        userDto.email(),
        userDto.phone(),
        null);
  }

}

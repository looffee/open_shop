package com.open.shop.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.UserDto;
import com.open.shop.model.db.User;

@Service
public class UserToUserDtoConverter implements Converter<User, UserDto> {

  public UserDto convert(@NonNull User user) {
    return new UserDto(
        user.id(),
        user.firstName(),
        user.lastName(),
        user.email(),
        user.phone());
  }

}

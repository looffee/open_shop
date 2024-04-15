package com.open.shop.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.UserAddressDto;
import com.open.shop.model.db.UserAddress;

@Service
public class UserAddressDtoToUserAddressConverter implements Converter<UserAddressDto, UserAddress> {

  public UserAddress convert(@NonNull UserAddressDto userAddressDto) {
    return new UserAddress(
        userAddressDto.id(),
        userAddressDto.userId(),
        userAddressDto.address(),
        userAddressDto.city(),
        userAddressDto.state(),
        userAddressDto.country(),
        userAddressDto.zipCode());
  }

}

package com.open.shop.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.UserAddressDto;
import com.open.shop.model.db.UserAddress;

@Service
public class UserAddressToUserAddressDtoConverter implements Converter<UserAddress, UserAddressDto> {

  public UserAddressDto convert(@NonNull UserAddress userAddress) {
    return new UserAddressDto(
        userAddress.id(),
        userAddress.userId(),
        userAddress.address(),
        userAddress.city(),
        userAddress.state(),
        userAddress.country(),
        userAddress.zipCode());
  }

}

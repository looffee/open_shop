package com.open.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.open.shop.model.api.UserAddressDto;
import com.open.shop.model.db.UserAddress;
import com.open.shop.repository.UserAdressRepository;

import reactor.core.publisher.Mono;

@Service
public class UserAddressService {

  @Autowired
  @NonNull
  UserAdressRepository userAddressRepository;

  @Autowired
  @NonNull
  ConversionService conversionService;

  public Mono<UserAddressDto> createUserAddress(UserAddressDto userAddress) {
    return userAddressRepository.save(
        new UserAddress(
            null,
            userAddress.userId(),
            userAddress.address(),
            userAddress.city(),
            userAddress.state(),
            userAddress.country(),
            userAddress.zipCode()))
        .map(savedAdress -> {
          return conversionService.convert(savedAdress, UserAddressDto.class);
        });
  }

}

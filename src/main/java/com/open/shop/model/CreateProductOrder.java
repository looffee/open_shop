package com.open.shop.model;

import com.open.shop.model.db.ProductOrderItem;
import com.open.shop.model.db.User;
import com.open.shop.model.db.UserAddress;

public record CreateProductOrder(
    User user,
    UserAddress userAddress,
    ProductOrderItem[] productOrderItems,
    long shippingTypeId,
    long paymentTypeId,
    String userNotes) {

}

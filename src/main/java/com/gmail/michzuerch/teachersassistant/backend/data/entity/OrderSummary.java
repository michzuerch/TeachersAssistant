package com.gmail.michzuerch.teachersassistant.backend.data.entity;

import com.gmail.michzuerch.teachersassistant.backend.data.OrderState;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface OrderSummary {
    Long getId();

    OrderState getState();

    Customer getCustomer();

    List<OrderItem> getItems();

    LocalDate getDueDate();

    LocalTime getDueTime();

    PickupLocation getPickupLocation();

    Integer getTotalPrice();
}

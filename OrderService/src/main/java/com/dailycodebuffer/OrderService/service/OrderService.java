package com.dailycodebuffer.OrderService.service;

import com.dailycodebuffer.OrderService.model.OrderRequest;
import com.dailycodebuffer.OrderService.model.OrderResponse;

import java.util.List;

public interface OrderService {
    Long placeOrder(OrderRequest orderReq);

    OrderResponse getOrderDetails(long orderId);

    List<OrderResponse> getAllOrders();
}

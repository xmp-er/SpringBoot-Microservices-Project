package com.dailycodebuffer.OrderService.service;

import com.dailycodebuffer.OrderService.model.OrderRequest;

public interface OrderService {
    Long placeOrder(OrderRequest orderReq);
}

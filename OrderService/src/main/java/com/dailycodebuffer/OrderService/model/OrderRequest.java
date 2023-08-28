package com.dailycodebuffer.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long productId;
    private Long totalAmount;
    private Long quantity;
    private PaymentMode paymentMode;
}

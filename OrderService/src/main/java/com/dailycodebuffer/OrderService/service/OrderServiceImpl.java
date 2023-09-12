package com.dailycodebuffer.OrderService.service;

import com.dailycodebuffer.OrderService.entity.Order;
import com.dailycodebuffer.OrderService.external.client.PaymentService;
import com.dailycodebuffer.OrderService.external.client.ProductService;
import com.dailycodebuffer.OrderService.external.request.PaymentRequest;
import com.dailycodebuffer.OrderService.model.OrderRequest;
import com.dailycodebuffer.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;
    @Override
    public Long placeOrder(OrderRequest orderReq) {

        log.info("Placing order request for the order with id:{}",orderReq.getProductId());

        productService.reduceQuantity(orderReq.getProductId(),orderReq.getQuantity());

        log.info("Creating Order with status CREATED");
        Order od = new Order();
        od.setOrderStatus("CREATED");
        od.setOrderDate(Instant.now());
        od.setQuantity(orderReq.getQuantity());
        od.setAmount(orderReq.getTotalAmount());
        od.setProductId(orderReq.getProductId());

        od = orderRepo.save(od);

        log.info("Calling Payment Service to complete the payment");

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setPaymentMode(orderReq.getPaymentMode());
        paymentRequest.setOrderId(orderReq.getProductId());
        paymentRequest.setAmount(orderReq.getTotalAmount());

        String orderStatus = null;
        try{
            paymentService.doPayment(paymentRequest);
            log.info("Payment done successfully,changing the order status");
            orderStatus = "PLACED";
        }catch(Exception e){
            log.error("Error occurred in payment,changing order status to PAYMENT_FAILED");
            orderStatus = "PAYMENT_FAILED";
        }

        od.setOrderStatus(orderStatus);
        orderRepo.save(od);


        log.info("Order successfully place with the details {}",od);
        return od.getId();
    }
}

package com.example.PaymentService.service;

import com.example.PaymentService.entity.TransactionDetails;
import com.example.PaymentService.model.PaymentRequest;
import com.example.PaymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{

    private TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording payment details {}",paymentRequest);

        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setPaymentMode(paymentRequest.getPaymentMode().name());
        transactionDetails.setAmount(paymentRequest.getAmount());
        transactionDetails.setId(paymentRequest.getOrderId());
        transactionDetails.setPaymentStatus("SUCCESS");
        transactionDetails.setReferenceNumber(paymentRequest.getReferenceNumber());

        transactionDetailsRepository.save(transactionDetails);

        log.info("Transaction completed with Id: {}",transactionDetails.getId());

        return transactionDetails.getId();
    }
}

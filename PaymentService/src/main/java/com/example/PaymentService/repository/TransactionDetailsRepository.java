package com.example.PaymentService.repository;

import com.example.PaymentService.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails,Long> {
}

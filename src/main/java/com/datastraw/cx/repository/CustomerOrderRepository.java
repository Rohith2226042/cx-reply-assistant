package com.datastraw.cx.repository;

import com.datastraw.cx.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    Optional<CustomerOrder> findByCustomerIdAndBrandId(Long customerId, Long brandId);
}
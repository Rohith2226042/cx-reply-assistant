package com.datastraw.cx.repository;

import com.datastraw.cx.entity.BrandPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandPolicyRepository extends JpaRepository<BrandPolicy, Long> {

    List<BrandPolicy> findByBrandId(Long brandId);

    List<BrandPolicy> findByBrandIdAndPolicyTypeIn(
            Long brandId,
            List<String> policyTypes
    );
}
package com.datastraw.cx.service;

import com.datastraw.cx.entity.BrandPolicy;
import com.datastraw.cx.repository.BrandPolicyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyRetrievalService {

    private final BrandPolicyRepository policyRepository;

    public PolicyRetrievalService(BrandPolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public List<BrandPolicy> retrieveRelevantPolicies(
            Long brandId,
            String conversationText) {

        String text = conversationText.toLowerCase();

        List<String> policyTypes = new ArrayList<>();

        /*
         * REFUND
         */
        if (text.contains("refund")
                || text.contains("money back")
                || text.contains("damaged")
                || text.contains("broken")) {

            policyTypes.add("REFUND");
        }

        /*
         * RETURN / REPLACEMENT
         */
        if (text.contains("replacement")
                || text.contains("replace")
                || text.contains("return")
                || text.contains("damaged")
                || text.contains("broken")) {

            policyTypes.add("RETURN");
        }

        /*
         * SHIPPING
         */
        if (text.contains("delivery")
                || text.contains("shipping")
                || text.contains("late")
                || text.contains("delivered")
                || text.contains("shipment")) {

            policyTypes.add("SHIPPING");
        }

        /*
         * CANCELLATION
         */
        if (text.contains("cancel")
                || text.contains("cancellation")) {

            policyTypes.add("CANCELLATION");
        }

        /*
         * No relevant policy found.
         */
        if (policyTypes.isEmpty()) {
            return List.of();
        }

        return policyRepository.findByBrandIdAndPolicyTypeIn(
                brandId,
                policyTypes
        );
    }
}
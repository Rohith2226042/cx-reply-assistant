package com.datastraw.cx.config;

import com.datastraw.cx.entity.*;
import com.datastraw.cx.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadDemoData(
            BrandRepository brandRepository,
            CustomerRepository customerRepository,
            CustomerOrderRepository orderRepository,
            ConversationRepository conversationRepository,
            MessageRepository messageRepository,
            BrandPolicyRepository policyRepository) {

        return args -> {

            if (brandRepository.count() > 0) {
                return;
            }

            // Brand
            Brand brand = brandRepository.save(
                    Brand.builder()
                            .name("AquaPure")
                            .description("Premium bottled water and beverages brand")
                            .build()
            );

            // Customer
            Customer customer = customerRepository.save(
                    Customer.builder()
                            .name("Rahul Sharma")
                            .email("rahul@example.com")
                            .phone("+91 9876543210")
                            .build()
            );

            // Order
            CustomerOrder order = orderRepository.save(
                    CustomerOrder.builder()
                            .orderNumber("AP-10001")
                            .productName("AquaPure Mineral Water - 12 Pack")
                            .status("DELIVERED")
                            .orderDate(LocalDate.of(2026, 8, 20))
                            .deliveryDate(LocalDate.of(2026, 8, 25))
                            .customer(customer)
                            .brand(brand)
                            .build()
            );

            // Conversation
            Conversation conversation = conversationRepository.save(
                    Conversation.builder()
                            .customer(customer)
                            .brand(brand)
                            .channel("WHATSAPP")
                            .createdAt(LocalDateTime.now())
                            .build()
            );

            // Customer message
            messageRepository.save(
                    Message.builder()
                            .conversation(conversation)
                            .content("Hi, my order was delivered but one of the bottles is broken.")
                            .senderType("CUSTOMER")
                            .createdAt(LocalDateTime.now().minusMinutes(10))
                            .build()
            );

            // Customer follow-up
            messageRepository.save(
                    Message.builder()
                            .conversation(conversation)
                            .content("Can I get a replacement or refund?")
                            .senderType("CUSTOMER")
                            .createdAt(LocalDateTime.now().minusMinutes(5))
                            .build()
            );

            // Brand policies
            policyRepository.save(
                    BrandPolicy.builder()
                            .brand(brand)
                            .policyType("REFUND")
                            .content("Refunds are available for damaged products reported within 7 days of delivery.")
                            .build()
            );

            policyRepository.save(
                    BrandPolicy.builder()
                            .brand(brand)
                            .policyType("RETURN")
                            .content("Damaged products are eligible for replacement within 7 days of delivery. Customers may be asked to provide photos of the damage.")
                            .build()
            );

            policyRepository.save(
                    BrandPolicy.builder()
                            .brand(brand)
                            .policyType("SHIPPING")
                            .content("Standard delivery usually takes 3 to 5 business days.")
                            .build()
            );

            System.out.println("Demo CX data loaded successfully.");
        };
    }
}
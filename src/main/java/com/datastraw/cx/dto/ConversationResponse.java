package com.datastraw.cx.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ConversationResponse(
        Long conversationId,
        CustomerDto customer,
        BrandDto brand,
        OrderDto order,
        String channel,
        LocalDateTime createdAt,
        List<MessageDto> messages,
        List<PolicyDto> policies
) {

    public record CustomerDto(
            Long id,
            String name,
            String email,
            String phone
    ) {}

    public record BrandDto(
            Long id,
            String name,
            String description
    ) {}

    public record OrderDto(
            Long id,
            String orderNumber,
            String productName,
            String status,
            LocalDate orderDate,
            LocalDate deliveryDate
    ) {}

    public record MessageDto(
            Long id,
            String content,
            String senderType,
            LocalDateTime createdAt
    ) {}

    public record PolicyDto(
            Long id,
            String policyType,
            String content
    ) {}
}
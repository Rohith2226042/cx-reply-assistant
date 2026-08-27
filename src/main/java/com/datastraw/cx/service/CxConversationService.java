package com.datastraw.cx.service;

import com.datastraw.cx.dto.ConversationResponse;
import com.datastraw.cx.entity.*;
import com.datastraw.cx.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CxConversationService {

    private final ConversationRepository conversationRepository;
    private final CustomerOrderRepository orderRepository;
    private final MessageRepository messageRepository;
    private final BrandPolicyRepository policyRepository;

    public CxConversationService(
            ConversationRepository conversationRepository,
            CustomerOrderRepository orderRepository,
            MessageRepository messageRepository,
            BrandPolicyRepository policyRepository) {

        this.conversationRepository = conversationRepository;
        this.orderRepository = orderRepository;
        this.messageRepository = messageRepository;
        this.policyRepository = policyRepository;
    }

    public ConversationResponse getConversation(Long conversationId) {

        Conversation conversation = conversationRepository
                .findById(conversationId)
                .orElseThrow(() ->
                        new RuntimeException("Conversation not found"));

        Customer customer = conversation.getCustomer();
        Brand brand = conversation.getBrand();

        CustomerOrder order = orderRepository
                .findByCustomerIdAndBrandId(
                        customer.getId(),
                        brand.getId()
                )
                .orElse(null);

        List<Message> messages =
                messageRepository
                        .findByConversationIdOrderByCreatedAtAsc(
                                conversationId
                        );

        List<BrandPolicy> policies =
                policyRepository.findByBrandId(brand.getId());

        return new ConversationResponse(

                conversation.getId(),

                new ConversationResponse.CustomerDto(
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getPhone()
                ),

                new ConversationResponse.BrandDto(
                        brand.getId(),
                        brand.getName(),
                        brand.getDescription()
                ),

                order == null ? null :
                        new ConversationResponse.OrderDto(
                                order.getId(),
                                order.getOrderNumber(),
                                order.getProductName(),
                                order.getStatus(),
                                order.getOrderDate(),
                                order.getDeliveryDate()
                        ),

                conversation.getChannel(),
                conversation.getCreatedAt(),

                messages.stream()
                        .map(message ->
                                new ConversationResponse.MessageDto(
                                        message.getId(),
                                        message.getContent(),
                                        message.getSenderType(),
                                        message.getCreatedAt()
                                ))
                        .toList(),

                policies.stream()
                        .map(policy ->
                                new ConversationResponse.PolicyDto(
                                        policy.getId(),
                                        policy.getPolicyType(),
                                        policy.getContent()
                                ))
                        .toList()
        );
    }

    public Conversation getConversationEntity(Long conversationId) {

        return conversationRepository.findById(conversationId)
                .orElseThrow(() ->
                        new RuntimeException("Conversation not found"));
    }
}
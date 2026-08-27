package com.datastraw.cx.controller;

import com.datastraw.cx.dto.ConversationResponse;
import com.datastraw.cx.service.CxConversationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cx/conversations")
public class CxConversationController {

    private final CxConversationService conversationService;

    public CxConversationController(
            CxConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping("/{conversationId}")
    public ConversationResponse getConversation(
            @PathVariable Long conversationId) {

        return conversationService
                .getConversation(conversationId);
    }
}
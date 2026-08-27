package com.datastraw.cx.controller;

import com.datastraw.cx.dto.ReplyDraftResponse;
import com.datastraw.cx.service.AiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/reply/{conversationId}")
    public ReplyDraftResponse generateReply(
            @PathVariable Long conversationId) {

        return aiService.generateReply(conversationId);
    }

    @PostMapping("/reply/{conversationId}/regenerate")
    public ReplyDraftResponse regenerateReply(
            @PathVariable Long conversationId) {

        return aiService.generateReply(conversationId);
    }
}
package com.datastraw.cx.controller;

import com.datastraw.cx.dto.ReplyEditRequest;
import com.datastraw.cx.entity.ReplyDraft;
import com.datastraw.cx.service.ReplyDraftService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/replies")
public class ReplyDraftController {

    private final ReplyDraftService replyDraftService;

    public ReplyDraftController(
            ReplyDraftService replyDraftService) {
        this.replyDraftService = replyDraftService;
    }

    @PutMapping("/{draftId}")
    public ReplyDraft editReply(
            @PathVariable Long draftId,
            @RequestBody ReplyEditRequest request) {

        return replyDraftService.editReply(
                draftId,
                request.editedResponse()
        );
    }

    @PostMapping("/{draftId}/approve")
    public ReplyDraft approveReply(
            @PathVariable Long draftId) {

        return replyDraftService.approveReply(draftId);
    }
}
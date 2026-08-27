package com.datastraw.cx.dto;

public record ReplyDraftResponse(
        Long draftId,
        String response,
        String status
) {
}
package com.datastraw.cx.service;

import com.datastraw.cx.entity.ReplyDraft;
import com.datastraw.cx.repository.ReplyDraftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReplyDraftService {

    private final ReplyDraftRepository replyDraftRepository;

    public ReplyDraftService(
            ReplyDraftRepository replyDraftRepository) {
        this.replyDraftRepository = replyDraftRepository;
    }

    public ReplyDraft editReply(
            Long draftId,
            String editedResponse) {

        ReplyDraft draft = replyDraftRepository
                .findById(draftId)
                .orElseThrow(() ->
                        new RuntimeException("Reply draft not found"));

        draft.setEditedResponse(editedResponse);
        draft.setStatus("EDITED");
        draft.setUpdatedAt(LocalDateTime.now());

        return replyDraftRepository.save(draft);
    }

    public ReplyDraft approveReply(Long draftId) {

        ReplyDraft draft = replyDraftRepository
                .findById(draftId)
                .orElseThrow(() ->
                        new RuntimeException("Reply draft not found"));

        String finalResponse = draft.getEditedResponse();

        if (finalResponse == null ||
                finalResponse.isBlank()) {

            finalResponse = draft.getGeneratedResponse();
        }

        draft.setFinalResponse(finalResponse);
        draft.setStatus("APPROVED");
        draft.setUpdatedAt(LocalDateTime.now());

        return replyDraftRepository.save(draft);
    }
}
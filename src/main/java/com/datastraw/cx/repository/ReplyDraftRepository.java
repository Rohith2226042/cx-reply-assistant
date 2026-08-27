package com.datastraw.cx.repository;

import com.datastraw.cx.entity.ReplyDraft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyDraftRepository extends JpaRepository<ReplyDraft, Long> {

    List<ReplyDraft> findByConversationIdOrderByCreatedAtDesc(
            Long conversationId
    );

    Optional<ReplyDraft> findTopByConversationIdOrderByCreatedAtDesc(
            Long conversationId
    );
}
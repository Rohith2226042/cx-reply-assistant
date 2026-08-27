package com.datastraw.cx.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reply_drafts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String customerMessage;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String retrievedContext;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String generatedResponse;

    @Column(columnDefinition = "TEXT")
    private String editedResponse;

    @Column(columnDefinition = "TEXT")
    private String finalResponse;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
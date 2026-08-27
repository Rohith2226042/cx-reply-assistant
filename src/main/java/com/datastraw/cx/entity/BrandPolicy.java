package com.datastraw.cx.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "brand_policies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(nullable = false)
    private String policyType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
}
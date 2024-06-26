package com.dadingcoding.web.domain;

import jakarta.persistence.*;

@Entity
public class Notice extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}

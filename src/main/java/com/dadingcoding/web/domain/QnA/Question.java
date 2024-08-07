package com.dadingcoding.web.domain.QnA;

import com.dadingcoding.web.domain.BaseEntity;
import com.dadingcoding.web.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Question(Member member, String content) {
        this.member = member;
        this.content = content;
    }
}
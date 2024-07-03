package com.dadingcoding.web.domain;

import com.dadingcoding.web.service.StringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity @Builder @Getter
@AllArgsConstructor @NoArgsConstructor
public class Interview extends PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interviewee_id")
    private Member interviewee;

    private String scheduleTime;
}

package com.dadingcoding.web.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Builder @Getter
@AllArgsConstructor @NoArgsConstructor
public class Schedule extends PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Member mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Member mentee;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_time_list")
    private List<ScheduleTime> scheduleTimeList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;
}

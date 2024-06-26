package com.dadingcoding.web.controller.dto.response;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Report;
import com.dadingcoding.web.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class ReportDetailResponseDto {
    /**
     * {
     *   "report_id": "1",
     *   "tutor_id": "3",
     *   "tutor_name": "김채영",
     *   "content": "이번 차시는 반복문을 다뤘음.....",
     *   "date": "2024-06-15",
     *   "students": [
     *     {
     *       "student_id": "10",
     *       "name": "김ㅇㅇ"
     *     }
     *   ]
     *   }
     */

    private Long reportId;
    private Long tutorId;
    private String tutorName;
    private String content;
    private LocalDateTime date;
    private List<SimpleReportStudentDto> students;

    public static ReportDetailResponseDto toDto(Report report) {
        Schedule schedule = report.getSchedule();
        Member student = schedule.getMentee();
        SimpleReportStudentDto studentDto = SimpleReportStudentDto.toDto(student);

        return ReportDetailResponseDto.builder()
                .reportId(report.getId())
                .tutorId(schedule.getMentee().getId())
                .tutorName(report.getMember().getUsername())
                .content(report.getContent())
                .date(report.getCreatedAt())
                .students(List.of(studentDto))
                .build();
    }
}

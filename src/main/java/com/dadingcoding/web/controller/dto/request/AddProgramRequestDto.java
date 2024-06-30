package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.Program;
import com.dadingcoding.web.domain.ProgramStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class AddProgramRequestDto {
    /**
     * {
     *   "title": "string",
     *   "description": "string",
     *   "program_pic": "string",
     *   "tutors": ["string"],
     *   "start_date": "string",
     *   "end_date": "string",
     *   "status": "string", // "ongoing", "completed"
     *   "details": "string"
     * }
     */

    private String title;
    private String description;
    private String program_pic;
    private List<Long> tutors;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private ProgramStatus status;
    private String details;

    public Program toEntity() {
        return Program.builder()
                .title(title)
                .description(description)
                .programPic(program_pic)
                .startDate(start_date)
                .endDate(end_date)
                .status(status)
                .details(details)
                .build();

    }
}

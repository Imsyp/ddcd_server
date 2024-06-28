package com.dadingcoding.web.service;

import com.dadingcoding.web.controller.dto.request.AddProgramRequestDto;
import com.dadingcoding.web.controller.dto.request.EditProgramRequestDto;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Program;
import com.dadingcoding.web.domain.ProgramMember;
import com.dadingcoding.web.repository.MemberRepository;
import com.dadingcoding.web.repository.ProgramMemberRepository;
import com.dadingcoding.web.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgramService {
    private final MemberRepository memberRepository;
    private final ProgramRepository programRepository;
    private final ProgramMemberRepository programMemberRepository;

    @Transactional
    public void addProgram(AddProgramRequestDto requestDto) {
        Program program = requestDto.toEntity();
        programRepository.save(program);

        List<Long> mentorIds = requestDto.getTutors();

        for (Long tutorId : mentorIds) {
            Member mentor = memberRepository.findById(tutorId)
                    .orElseThrow(() -> new NoSuchElementException("찾는 튜터가 없습니다."));

            ProgramMember programMember = ProgramMember.builder()
                    .member(mentor)
                    .program(program)
                    .build();

            programMemberRepository.save(programMember);
        }
    }

    public void editProgram(Long programId, EditProgramRequestDto requestDto) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new NoSuchElementException("프로그램이 존재하지 않습니다."));

        List<Long> afterMemberIds = requestDto.getTutors();
        Collections.sort(afterMemberIds);

        List<Long> orgMemberIds = program.getProgramMembers().stream()
                .map(m -> m.getMember().getId()).collect(Collectors.toList());
        Collections.sort(orgMemberIds);

        // Member 변경 발생
        if (!orgMemberIds.equals(afterMemberIds)) {
            // 모든 멤버 삭제 후 저장
            programMemberRepository.deleteAllById(orgMemberIds);

            for (Long tutorId : afterMemberIds) {
                Member mentor = memberRepository.findById(tutorId)
                        .orElseThrow(() -> new NoSuchElementException("찾는 튜터가 없습니다."));

                ProgramMember programMember = ProgramMember.builder()
                        .member(mentor)
                        .program(program)
                        .build();

                programMemberRepository.save(programMember);
            }

        }




    }

    public void deleteProgram(Long programId) {
        programRepository.deleteById(programId);
    }
}

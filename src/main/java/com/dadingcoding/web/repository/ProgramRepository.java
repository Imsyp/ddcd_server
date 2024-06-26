package com.dadingcoding.web.repository;

import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
}

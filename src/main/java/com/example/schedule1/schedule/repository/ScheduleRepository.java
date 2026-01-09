package com.example.schedule1.schedule.repository;

import com.example.schedule1.schedule.dto.SchedulePageResponse;
import com.example.schedule1.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findByUserId(Long userId);

    //Page<SchedulePageResponse> findSchedulePage(Pageable );
}

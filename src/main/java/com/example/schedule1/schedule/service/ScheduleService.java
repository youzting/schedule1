package com.example.schedule1.schedule.service;

import com.example.schedule1.schedule.dto.*;
import com.example.schedule1.schedule.entity.Schedule;
import com.example.schedule1.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request){
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getText()
        );
        Schedule saved = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getText()
        );
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAll(){
        List<Schedule> schedules = scheduleRepository.findAll();
        List<GetScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getText(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long scheduleId){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 없음")
        );
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getText(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional()
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 없음")
        );
        schedule.update(request.getText());
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getText(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional()
    public void delete(Long scheduleId){
        boolean existence = scheduleRepository.existsById(scheduleId);
        if (!existence) {
            throw new IllegalStateException("일정이 없음");
        }
        scheduleRepository.deleteById(scheduleId);
    }

}

package com.example.schedule1.schedule.service;

import com.example.schedule1.comment.repository.CommentRepository;
import com.example.schedule1.schedule.dto.*;
import com.example.schedule1.schedule.entity.Schedule;
import com.example.schedule1.schedule.repository.ScheduleRepository;
import com.example.schedule1.user.entity.User;
import com.example.schedule1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CreateScheduleResponse save(Long userId, CreateScheduleRequest request){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getText(),
                user
        );
        Schedule saved = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getText(),
                userId
        );
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAll(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );
        List<Schedule> schedules = scheduleRepository.findByUserId(userId);
        List<GetScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getText(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt(),
                    userId
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long userId ,Long scheduleId){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 없음")
        );
        if(!userId.equals(schedule.getUser().getId())){
            throw new IllegalStateException("유저의 일정이 없습니다.");
        }
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getText(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                userId
        );
    }

    @Transactional(readOnly = true)
    public Page<SchedulePageResponse> getSchedulePage(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, "modifiedAt") // 수정일 내림차순
        );

        Page<Schedule> result = scheduleRepository.findAll(pageable);

        return result.map(s -> new SchedulePageResponse(
                s.getId(),
                s.getTitle(),
                s.getText(),
                s.getUser().getUsername(),
                commentRepository.countByScheduleId(s.getId()),
                s.getCreatedAt(),
                s.getModifiedAt()
        ));
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

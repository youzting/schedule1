package com.example.schedule1.schedule.controller;

import com.example.schedule1.comment.repository.CommentRepository;
import com.example.schedule1.schedule.dto.*;
import com.example.schedule1.schedule.entity.Schedule;
import com.example.schedule1.schedule.repository.ScheduleRepository;
import com.example.schedule1.schedule.service.ScheduleService;
import com.example.schedule1.user.dto.LoginRequest;
import com.example.schedule1.user.dto.LoginResponse;
import com.example.schedule1.user.dto.SessionUser;
import com.example.schedule1.user.entity.User;
import com.example.schedule1.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;


    @PostMapping("/users/{userId}/schedules")
    public ResponseEntity<CreateScheduleResponse> create(@PathVariable Long userId, @Valid @RequestBody CreateScheduleRequest request, @SessionAttribute(name = "loginUser") SessionUser sessionUser){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(userId, request));
    }

    @GetMapping("/users/{userId}/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAll(@PathVariable Long userId,  @SessionAttribute(name = "loginUser") SessionUser sessionUser) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAll(userId));
    }

    @GetMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOne(@PathVariable Long userId, @PathVariable Long scheduleId,  @SessionAttribute(name = "loginUser") SessionUser sessionUser){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(userId, scheduleId));
    }

    @GetMapping("/schedules")
    public Page<SchedulePageResponse> getSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser
    ) {
        return scheduleService.getSchedulePage(page, size);
    }

    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> update(@PathVariable Long scheduleId, @RequestBody UpdateScheduleRequest request,  @SessionAttribute(name = "loginUser") SessionUser sessionUser){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> delete(@PathVariable Long scheduleId,  @SessionAttribute(name = "loginUser") SessionUser sessionUser){
        scheduleService.delete(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

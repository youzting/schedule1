package com.example.schedule1.schedule.controller;

import com.example.schedule1.schedule.dto.*;
import com.example.schedule1.schedule.entity.Schedule;
import com.example.schedule1.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/users/{userId}/schedules")
    public ResponseEntity<CreateScheduleResponse> create(@PathVariable Long userId, @Valid @RequestBody CreateScheduleRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(userId, request));
    }

    @GetMapping("/users/{userId}/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAll(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAll(userId));
    }

    @GetMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOne(@PathVariable Long scheduleId){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(scheduleId));
    }

    @PutMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> update(@PathVariable Long scheduleId, @RequestBody UpdateScheduleRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }

    @DeleteMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<Void> delete(@PathVariable Long scheduleId){
        scheduleService.delete(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

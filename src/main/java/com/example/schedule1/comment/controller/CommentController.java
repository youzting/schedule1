package com.example.schedule1.comment.controller;

import com.example.schedule1.comment.dto.CreateCommentRequest;
import com.example.schedule1.comment.dto.CreateCommentResponse;
import com.example.schedule1.comment.dto.GetCommentResponse;
import com.example.schedule1.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    //{userId}는 작성자Id
    @PostMapping("/users/{userId}/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> create(
            @PathVariable Long userId,
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(userId, scheduleId, request));
    }

    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<GetCommentResponse>> getAll(@PathVariable Long scheduleId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAll(scheduleId));
    }

}

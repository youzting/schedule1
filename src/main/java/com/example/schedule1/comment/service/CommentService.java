package com.example.schedule1.comment.service;

import com.example.schedule1.comment.dto.CreateCommentRequest;
import com.example.schedule1.comment.dto.CreateCommentResponse;
import com.example.schedule1.comment.dto.GetCommentResponse;
import com.example.schedule1.comment.entity.Comment;
import com.example.schedule1.comment.repository.CommentRepository;
import com.example.schedule1.schedule.entity.Schedule;
import com.example.schedule1.schedule.repository.ScheduleRepository;
import com.example.schedule1.user.entity.User;
import com.example.schedule1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateCommentResponse save(Long userId, Long scheduleId, CreateCommentRequest request){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 없습니다.")
        );
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("유저가 없습니다.")
        );
        //User,Schedule 엔티티를 Comment에 연결
        Comment comment = new Comment(
                request.getText(),
                user,
                schedule

        );
       //userId저장
        Comment saveComment = commentRepository.save(comment);
        return new CreateCommentResponse(
                saveComment.getId(),
                saveComment.getText(),
                saveComment.getCreatedAt(),
                saveComment.getModifiedAt(),
                userId,
                scheduleId
        );
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponse> getAll(Long scheduleId){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 없습니다.")
        );
        List<Comment> comments = commentRepository.findByScheduleId(scheduleId);
        List<GetCommentResponse> dtos = new ArrayList<>();
        for(Comment comment : comments){
            GetCommentResponse dto = new GetCommentResponse(
                    comment.getId(),
                    comment.getText(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt(),
                    comment.getUser().getId()
            );
            dtos.add(dto);
        }
        return dtos;
    }

}

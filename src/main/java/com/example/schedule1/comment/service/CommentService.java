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
    public CreateCommentResponse save(Long scheduleId, Long userId, CreateCommentRequest request){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 없습니다.")
        );
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("유저가 없습니다.")
        ) ;
        Comment comment = new Comment(
                request.getText(),
                schedule,
                user
        );
        Comment saveComment = commentRepository.save(comment);
        return new CreateCommentResponse(
                saveComment.getId(),
                saveComment.getText(),
                saveComment.getCreatedAt(),
                saveComment.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponse> getAll(Long scheduleId){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 없습니다.")
        );
        List<Comment> comments = commentRepository.findAll();
        List<GetCommentResponse> dtos = new ArrayList<>();
        for(Comment comment : comments){
            GetCommentResponse dto = new GetCommentResponse(
                    comment.getId(),
                    comment.getText(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

}

package com.example.schedule1.comment.repository;

import com.example.schedule1.comment.entity.Comment;
import com.example.schedule1.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByScheduleId(Long scheduleId);
}

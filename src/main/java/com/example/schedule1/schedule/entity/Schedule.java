package com.example.schedule1.schedule.entity;

import com.example.schedule1.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public Schedule(String title, String text, User user){
        this.title = title;
        this.text = text;
        this.user = user;
    }

    public void update(String text) {
        this.text = text;
    }

}

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

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name="user_id")
    //private User user;

    private Long id;
    private String title;
    private String text;

    public Schedule(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public void update(String text) {
        this.text = text;
    }



}

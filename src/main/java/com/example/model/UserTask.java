package com.example.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user", "task"})
@Entity
@Table(name = "volunteer_task")
public class UserTask implements Serializable {

    @Id
    @JoinColumn(name = "volunteer_id")
    @ManyToOne
    private User user;

    @Id
    @JoinColumn(name = "task_id")
    @ManyToOne
    private Task task;

    @JoinColumn(name = "is_creator")
    private boolean userCreator;

    @Column(name = "participation_date")
    private LocalDate participationDate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "approved")
    private boolean approved;

}
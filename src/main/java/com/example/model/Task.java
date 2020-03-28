package com.example.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"userTasks"})
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "title")
    private String title;

    @Column(name = "participants")
    private int numberOfParticipants;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "task")
    private Set<UserTask> userTasks = new HashSet<>();

}
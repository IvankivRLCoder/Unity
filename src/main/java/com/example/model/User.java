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
@ToString(exclude = {"participatedTasks"})
@Entity
@Table(name = "volunteer")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "volunteer_name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "trust_level")
    private TrustLevel trustLevel;

    @Column(name = "is_blocked")
    private boolean blocked;

    @OneToMany(mappedBy = "user")
    private Set<UserTask> participatedTasks = new HashSet<>();

}
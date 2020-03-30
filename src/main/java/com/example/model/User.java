package com.example.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
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
    private int id;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserTask> participatedTasks = new HashSet<>();

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        User user = (User) other;
        return blocked == user.blocked
                && Objects.equals(id, user.id)
                && Objects.equals(name, user.name)
                && Objects.equals(surname, user.surname)
                && Objects.equals(email, user.email)
                && Objects.equals(password, user.password)
                && Objects.equals(phoneNumber, user.phoneNumber)
                && Objects.equals(dateOfBirth, user.dateOfBirth)
                && trustLevel == user.trustLevel
                && Objects.equals(participatedTasks, user.participatedTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
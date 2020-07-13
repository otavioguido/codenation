package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<Candidate> candidates;

    @OneToMany
    private List<Submission> submissions;

    @Size(max = 100)
    @NotBlank
    @NotNull
    @Column(nullable = false, name = "full_name")
    private String fullName;

    @Size(max = 100)
    @NotNull
    @NotBlank
    @Email
    private String email;

    @Size(max = 50)
    @NotNull
    @NotBlank
    private String nickname;

    @Size(max = 255)
    @NotNull
    @NotBlank
    private String password;

    @CreatedDate
    @NotBlank
    @NotNull
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
}

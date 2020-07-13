package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<Candidate> candidates;

    @Size(max = 100)
    @NotNull
    @NotBlank
    private String name;

    @Size(max = 50)
    @NotNull
    @NotBlank
    private String slug;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    @NotNull
    @NotBlank
    private Date createdAt;
}

package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Candidate {

    @EmbeddedId
    private CandidateIndetity candidateIndetity;

    @NotNull
    @NotBlank
    private Integer status;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    @NotNull
    @NotBlank
    private Date created_at;
}

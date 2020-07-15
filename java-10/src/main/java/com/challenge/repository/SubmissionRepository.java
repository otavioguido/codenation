package com.challenge.repository;

import com.challenge.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    @Query("SELECT max(submission.score) FROM Submission submission " +
            "JOIN submission.id.challenge challenge " +
            "WHERE challenge.id = :challengeId")
    Optional<BigDecimal> findHigherScoreByChallengeId(@Param("challengeId") Long challengeId);

    @Query("SELECT submission FROM Submission submission " +
            "INNER JOIN Acceleration acceleration " +
            "ON acceleration.challenge.id = submission.id.challenge.id " +
            "WHERE submission.id.challenge.id = :challengeId AND acceleration.id = :accelerationId")
    List<Submission> findByChallengeIdAndAccelerationId(@Param("challengeId") Long challengeId, @Param("accelerationId") Long accelerationId);
}

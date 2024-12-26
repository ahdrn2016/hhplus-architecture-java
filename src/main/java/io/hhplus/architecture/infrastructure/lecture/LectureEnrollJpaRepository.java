package io.hhplus.architecture.infrastructure.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.hhplus.architecture.domain.lecture.LectureEnroll;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureEnrollJpaRepository extends JpaRepository<LectureEnroll, Long> {

    @Query("select le from LectureEnroll le where le.userId = :userId")
    List<Lecture> findEnrolledLecturesByUserId(Long userId);

    /**
     * SELECT *
     *   FROM LECTURE_ENROLL
     *  WHERE :START_DTM BETWEEN START_DTM AND END_DTM
     *     OR :END_DTM BETWEEN START_DTM AND END_DTM
     */
    @Query("select le from LectureEnroll le where :startDtm between le.startDtm and le.endDtm or :endDtm between le.startDtm and le.endDtm")
    List<LectureEnroll> findDuplicateLectures(LocalDateTime startDtm, LocalDateTime endDtm);
}

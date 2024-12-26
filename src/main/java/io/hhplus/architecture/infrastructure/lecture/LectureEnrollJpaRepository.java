package io.hhplus.architecture.infrastructure.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.hhplus.architecture.domain.lecture.LectureEnroll;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureEnrollJpaRepository extends JpaRepository<LectureEnroll, Long> {

    List<LectureEnroll> findByUserId(Long userId);

    /**
     * SELECT LE.*
     *   FROM LECTURE_ENROLL LE
     *  WHERE :START_DTM BETWEEN LE.START_DTM AND LE.END_DTM
     *     OR :END_DTM BETWEEN LE.START_DTM AND LE.END_DTM
     */
    @Query("select le from LectureEnroll le where :startDtm between le.startDtm and le.endDtm or :endDtm between le.startDtm and le.endDtm")
    List<LectureEnroll> findDuplicateLectures(@Param("startDtm") LocalDateTime startDtm, @Param("endDtm") LocalDateTime endDtm);

    LectureEnroll findByUserIdAndLectureId(Long userId, Long lectureId);
}

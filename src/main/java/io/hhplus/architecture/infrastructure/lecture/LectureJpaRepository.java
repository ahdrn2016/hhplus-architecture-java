package io.hhplus.architecture.infrastructure.lecture;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import io.hhplus.architecture.domain.lecture.Lecture;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {

    @Query("select l from Lecture l where l.startDtm > current_timestamp")
    List<Lecture> findAfterNow();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select l from Lecture l where l.id = :lectureId")
    Lecture findByIdWithLock(@Param("lectureId") Long lectureId);
}

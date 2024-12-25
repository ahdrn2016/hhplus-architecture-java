package java.hhplus.architecture.infrastructure.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.hhplus.architecture.domain.lecture.Lecture;
import java.util.List;

public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {

    @Query("select l from Lecture l where l.startDtm > current_timestamp")
    List<Lecture> findAfterNow();

}
